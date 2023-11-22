package game.checkers.validator

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator


class EmptyPositionValidator : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if (gameState.getPieceMap().contains(movement.to)) {
            return InvalidMovementResult("To position is not empty")
        }
        return ValidMovementResult()
    }
}