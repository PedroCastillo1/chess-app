package game.checkers.state

import adt.*
import game.common.GameState
import game.common.piece.Colour
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
            if (gameState.checkersPieceHasAnyValidMovement(piece.value)) {
                return true
            }
        }
        return false
    }

}