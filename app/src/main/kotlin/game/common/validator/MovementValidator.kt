package game.common.validator

import adt.ResultMovement
import game.common.GameState
import game.common.movement.Movement

interface MovementValidator {
    fun validate(movement: Movement, gameState: GameState): ResultMovement
}