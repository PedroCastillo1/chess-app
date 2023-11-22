package game.common.validator.basic.movement

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator

class HorizontalMovementValidator : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if ((movement.from.column != movement.to.column) && (movement.from.row == movement.to.row)) {
            return ValidMovementResult()
        }
        return InvalidMovementResult("Piece is not moving correctly")
    }

}