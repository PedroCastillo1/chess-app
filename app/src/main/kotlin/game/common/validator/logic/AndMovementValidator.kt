package game.common.validator.logic

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator


class AndMovementValidator(
    val mvList: List<MovementValidator>
) : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        for (mv in mvList) {
            val auxResult = mv.validate(movement, gameState)
            if (auxResult is InvalidMovementResult) {
                return auxResult
            }
        }
        return ValidMovementResult()
    }
}