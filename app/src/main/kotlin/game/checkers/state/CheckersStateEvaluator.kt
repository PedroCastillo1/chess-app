package game.checkers.state

import adt.*
import game.checkers.validator.BasicCheckersValidator
import game.common.GameState
import game.common.board.Position
import game.common.movement.Movement
import game.common.piece.Colour
import game.common.piece.Piece
import game.common.state.StateEvaluator


class CheckersStateEvaluator : StateEvaluator {
    override fun validate(gameState: GameState): StateResult {
        if (canAPieceMove(gameState)) {
            if (colourHasNoMorePieces(gameState, Colour.WHITE)) {
                return WinStateResult(Colour.BLACK)
            }
            if (colourHasNoMorePieces(gameState, Colour.BLACK)) {
                return WinStateResult(Colour.WHITE)
            }
            return InProgressStateResult()
        } else {
            return TieStateResult("Stalemate")
        }
    }

    private fun colourHasNoMorePieces(gameState: GameState, colour: Colour): Boolean {
        return gameState.getPieceMap().entries.none { it.value.colour === colour }
    }

    private fun canAPieceMove(gameState: GameState): Boolean {
        val pieceList = gameState.getPieceMap().entries.filter { it.value.colour === gameState.getCurrentColour() }
        for (piece in pieceList) {
            if (checkersPieceHasAnyValidMovement(gameState, piece.value)) {
                return true
            }
        }
        return false
    }

    private fun checkersPieceHasAnyValidMovement(gameState: GameState, piece: Piece): Boolean {
        val fromPosition = gameState.board.pieceMap.filterKeys { it == gameState.getPositionByPieceID(piece.id) }.keys.first()
        for (i in 1..gameState.board.numCol) {
            for (j in 1..gameState.board.numRow) {
                val toPosition = Position(i, j)
                val auxMovement = Movement(toPosition, fromPosition)
                if (BasicCheckersValidator().validate(auxMovement, gameState) is ValidMovementResult) {
                    if (piece.mv.validate(auxMovement, gameState) is ValidMovementResult) {
                        return true
                    }
                }
            }
        }
        return false
    }

}