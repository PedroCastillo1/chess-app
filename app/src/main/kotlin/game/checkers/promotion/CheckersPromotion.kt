package game.checkers.promotion

import game.common.board.Position
import game.common.GameState
import game.common.piece.Colour
import game.common.factory.PieceFactory
import game.common.promotion.PromotionStrategy

class CheckersPromotion : PromotionStrategy {
    override fun promote(gameState: GameState): GameState {
        val checkers = gameState.getPieceMap().filter { it.value.colour == gameState.getCurrentColour() && it.value.type == "PAWN" }
        val toRow = if(gameState.getCurrentColour() == Colour.WHITE) gameState.board.numRow else 1
        for ((position, piece) in checkers){
            val checkerPosition = position
            if (comparePositionsToRow(checkerPosition, toRow)){
                val auxPieceFactory = PieceFactory()
                val newCrowned = auxPieceFactory.crownedFactory(piece.id, gameState.getCurrentColour())
                var newMutableMap = gameState.getPieceMap().toMutableMap()
                newMutableMap.replace(checkerPosition, newCrowned)
                return gameState.copy(board = gameState.board.copy(pieceMap = newMutableMap))
            }
        }
        return gameState
    }


    private fun comparePositionsToRow(position: Position, row: Int): Boolean{
        return position.row == row
    }
}