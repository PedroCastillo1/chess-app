package game.common.validator.basic

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator


class NotSamePositionMovementValidator : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if ((movement.to.row === movement.from.row) && (movement.to.column === movement.from.column)) {
            return InvalidMovementResult("From and to are the same")
        }
        return ValidMovementResult()
    }
}