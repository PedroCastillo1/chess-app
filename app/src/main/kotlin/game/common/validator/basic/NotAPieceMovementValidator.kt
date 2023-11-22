package game.common.validator.basic

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator

class NotAPieceMovementValidator: MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if(gameState.getPieceMap().contains(movement.from)){
            return ValidMovementResult()
        }
        return InvalidMovementResult("No piece in from position")
    }
}