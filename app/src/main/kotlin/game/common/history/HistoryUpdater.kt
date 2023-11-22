package game.common.history

import game.common.GameState


class HistoryUpdater() {

    fun update(gameState: GameState): GameState {
        val auxBoard = gameState.board
        val newHistory = gameState.history.copy(boardHistory = gameState.history.boardHistory + auxBoard)
        return gameState.copy(history = newHistory)
    }

}