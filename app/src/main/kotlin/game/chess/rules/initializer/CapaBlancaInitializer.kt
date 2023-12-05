package game.chess.rules.initializer

import game.common.GameState
import game.common.factory.GameStateFactory
import game.common.initializer.GameInitializer

class CapaBlancaInitializer : GameInitializer {
    private val gameStateFactory = GameStateFactory()
    override fun init(): GameState {
        return gameStateFactory.capaBlancaStateBuilder()
    }

}
