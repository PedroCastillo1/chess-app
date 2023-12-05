package game.common.rules

import edu.austral.dissis.chess.gui.MoveResult
import adapter.Adapter
import adt.StateResult
import game.common.GameState
import game.common.movement.Movement

interface RulesChecker {
    fun makeAMove(movement: Movement, gameState: GameState): StateResult
    fun isMovementSuccessful(move: Movement, gameState: GameState): Boolean
}