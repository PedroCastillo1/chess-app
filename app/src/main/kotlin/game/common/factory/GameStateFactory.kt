package game.common.factory

import adt.InProgressStateResult
import game.common.GameState
import game.common.history.History
import game.common.turn.BasicTurnStrategy
import game.common.piece.Colour


class GameStateFactory {
    fun normalGameStateBuilder(): GameState {
        return GameState(BoardFactory().initialiceNormalBoard(), BasicTurnStrategy(Colour.WHITE), History(listOf()), InProgressStateResult())
    }

    fun checkersStateBuilder(): GameState {
        return GameState(BoardFactory().initialiceCheckersBoard(), BasicTurnStrategy(Colour.WHITE), History(listOf()), InProgressStateResult())
    }

    fun capaBlancaStateBuilder(): GameState {
        return GameState(BoardFactory().initialiceCapaBlancaBoard(), BasicTurnStrategy(Colour.WHITE), History(listOf()), InProgressStateResult())
    }

    fun emptyBoardGameStateBuilder(): GameState {
        return GameState(BoardFactory().initialiceBasicEmptyBoard(), BasicTurnStrategy(Colour.WHITE), History(listOf()), InProgressStateResult())
    }

}