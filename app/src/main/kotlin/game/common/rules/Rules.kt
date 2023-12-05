package game.common.rules

import edu.austral.dissis.chess.gui.MoveResult
import adapter.Adapter
import adt.StateResult
import game.common.GameState
import game.common.movement.Movement


interface Rules {
    fun init(): GameState
    fun makeAMove(move: Movement): StateResult
    fun getGameState(): GameState
}