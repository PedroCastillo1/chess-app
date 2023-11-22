package game.common.promotion

import game.common.GameState

interface PromotionStrategy {
    fun promote(gameState: GameState): GameState
}