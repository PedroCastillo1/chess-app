package game.common.movement

import game.common.GameState
import game.common.movement.Movement


interface MovementBehaviour {
    fun move(gameState: GameState, movement: Movement): GameState
}