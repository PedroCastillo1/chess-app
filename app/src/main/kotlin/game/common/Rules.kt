package game.common

import edu.austral.dissis.chess.gui.MoveResult
import game.common.movement.Movement


interface Rules {

    fun init(): GameState
    fun makeAMove(move: Movement): MoveResult
    fun getAdapter(): Adapter
}