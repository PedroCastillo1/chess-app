package game.common.movement

import game.common.GameState
import game.common.movement.Movement
import game.common.factory.BoardFactory
import game.common.movement.MovementBehaviour


class NormalMovementBehaviour() : MovementBehaviour {
    override fun move(gameState: GameState, movement: Movement): GameState {
        val boardFactory = BoardFactory()
        return gameState.copy(board = boardFactory.boardFromReference(gameState.board, movement))
    }

}