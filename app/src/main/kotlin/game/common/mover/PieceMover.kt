package game.common.mover

import game.common.GameState
import game.common.movement.Movement


class PieceMover {
    fun movePiece(movement: Movement, gameState: GameState): GameState {// Seprar funciones
        return gameState.getPiece(movement.from)!!.mb.move(gameState, movement)
    }
}