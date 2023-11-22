package game.common.validator.basic

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator


class OtherColourMovementValidator : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if (gameState.getPieceMap().containsKey(movement.to)) {
            if (gameState.getPieceMap().get(movement.to)!!.colour != gameState.getCurrentColour()) {
                return ValidMovementResult()
            }
        }
        return InvalidMovementResult("The to position is not occupied by a piece of another colour")
    }
}