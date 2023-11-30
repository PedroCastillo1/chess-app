package game.common.validator.basic.movement

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator
import kotlin.math.abs


class JumpMovementValidator(
        private val x: Int,
        private val y: Int,
) : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if (isMovementJumping(movement)) {
            return ValidMovementResult()
        }
        return InvalidMovementResult("Piece is not moving correctly")
    }
    private fun isMovementJumping(movement: Movement): Boolean {
        val auxX = abs(movement.to.column - movement.from.column)
        val auxY = abs(movement.to.row - movement.from.row)
        return (auxX == x && auxY == y) || (auxX == y && auxY == x)
    }
}