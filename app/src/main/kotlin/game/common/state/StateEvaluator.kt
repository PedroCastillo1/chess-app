package game.common.state

import adt.StateResult
import game.common.GameState

interface StateEvaluator {
    fun validate(gameState: GameState): StateResult

}