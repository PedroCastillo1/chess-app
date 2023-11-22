package game.common.validator.logic

import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator

class OrMovementValidator(
    val mvList: List<MovementValidator>
) : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        for (mv in mvList) {
            val auxResult = mv.validate(movement, gameState)
            if (auxResult is ValidMovementResult) {
                return auxResult
            }
        }
        return mvList.get(0).validate(movement, gameState)
    }
}
