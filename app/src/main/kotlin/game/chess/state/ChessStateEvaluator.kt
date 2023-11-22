package game.chess.state

import adt.*
import game.common.GameState
import game.common.movement.Movement
import game.common.piece.Colour
import game.common.factory.BoardFactory
import game.common.state.StateEvaluator


class ChessStateEvaluator :
    StateEvaluator {

    override fun validate(gameState: GameState): StateResult {
        if (isThereAWayOfMate(gameState)) {
            if (canAPieceMove(gameState)) {
                if (isCheckMate(gameState)) {
                    when (gameState.getCurrentColour()) {
                        Colour.BLACK -> return WinStateResult(Colour.BLACK)
                        Colour.WHITE -> return WinStateResult(Colour.WHITE)
                    }
                }else{
                    return InProgressStateResult()
                }
            }
            return TieStateResult("Stalemate")
        }

        return TieStateResult("Stalemate")
    }

    private fun canAPieceMove(gameState: GameState): Boolean {
        val pieceList = gameState.getPieceMap().entries.filter { it.value.colour === gameState.getCurrentColour() }
        for (piece in pieceList) {
            if (gameState.chessPieceHasAnyValidMovement(piece.value)) {
                return true
            }
        }
        return false
    }

    private fun isTherePieceType(gameState: GameState, pieceType: String): Boolean {
        return (gameState.getPieceMap().entries.filter { it.value.type === pieceType }.isNotEmpty())
    }

    private fun numberOfPieceTypeInColour(gameState: GameState, pieceType: String, colour: Colour): Int {
        return gameState.getPieceMap().entries.filter { (it.value.type === pieceType) && (it.value.colour === colour) }.size
    }

    private fun isThereAWayOfMate(gameState: GameState): Boolean {
        val hasRook = isTherePieceType(gameState, "ROOK")
        val hasPawn = isTherePieceType(gameState, "PAWN")
        val hasQueen = isTherePieceType(gameState, "QUEEN")

        val whiteBishops = numberOfPieceTypeInColour(gameState, "BISHOP", Colour.WHITE)
        val blackBishops = numberOfPieceTypeInColour(gameState, "BISHOP", Colour.BLACK)
        val whiteKnights = numberOfPieceTypeInColour(gameState, "KNIGHT", Colour.WHITE)
        val blackKnights = numberOfPieceTypeInColour(gameState, "KNIGHT", Colour.BLACK)

        if ((hasRook || hasPawn || hasQueen)) {//si no hay esto anda a fijarte abajo
            return true
        }

        if (whiteBishops >= 2 || blackBishops >= 2) {
            return true
        }

        if ((whiteBishops >= 1 && whiteKnights >= 1) || (blackBishops >= 1 && blackKnights >= 1)) {
            return true
        }

        return false
    }


    private fun isCheckMate(gameState: GameState): Boolean {
        val newGameState = gameState.copy(currColour = gameState.currColour.advanceTurn())
        if(newGameState.isKingThreaten(newGameState.getCurrentColour())){
            val pieceList = newGameState.getPieceMap().entries.filter { it.value.colour === newGameState.getCurrentColour() }
            for (piece in pieceList) {
                if (newGameState.chessPieceHasAnyValidMovement(piece.value)) {
                    return false
                }
            }
            return true
        }
        return false
    }


    private fun canMovementSaveKing(movement: Movement, gameState: GameState) : Boolean{
        val auxBoardFactory = BoardFactory()
        val movementMadeGs = gameState.copy(board = auxBoardFactory.boardFromReference(gameState.board, movement))
        return movementMadeGs.isKingThreaten(movementMadeGs.getCurrentColour())
    }

}