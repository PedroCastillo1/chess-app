package game.common.validator.basic.movement

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator


class VerticalMovementValidator : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if ((movement.from.row != movement.to.row) && (movement.from.column == movement.to.column)) {
            return ValidMovementResult()
        }
        return InvalidMovementResult("Piece is not moving correctly")
    }

}