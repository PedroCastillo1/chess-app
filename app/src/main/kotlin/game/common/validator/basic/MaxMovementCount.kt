package game.common.validator.basic

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.board.Position
import game.common.validator.MovementValidator


class MaxMovementCount(
    val max: Int,
    val pieceId: String
) : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        val filteredList = filterDifferent(gameState.history.getPosById(pieceId))
        if (filteredList.size-1 < max) {
            return ValidMovementResult()
        }
        return InvalidMovementResult("Piece reached max movement amount")
    }

    private fun filterDifferent(posList: List<Position>): List<Position> {
        val distinctPositions = posList.distinct()
        return distinctPositions
    }
}