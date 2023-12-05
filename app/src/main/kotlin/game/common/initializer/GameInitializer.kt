package game.common.initializer

import game.common.GameState

interface GameInitializer {
    fun init(): GameState
}