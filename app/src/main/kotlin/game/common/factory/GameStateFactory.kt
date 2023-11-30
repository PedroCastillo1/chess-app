package game.common.factory

import adt.InProgressStateResult
import game.common.GameState
import game.common.history.History
import game.common.turn.CommonTurnStrategy
import game.common.piece.Colour


class GameStateFactory {
    fun normalGameStateBuilder(): GameState {
        return GameState(BoardFactory().initialiceNormalBoard(), CommonTurnStrategy(Colour.WHITE), History(listOf()), InProgressStateResult())
    }

    fun checkersStateBuilder(): GameState {
        return GameState(BoardFactory().initialiceCheckersBoard(), CommonTurnStrategy(Colour.WHITE), History(listOf()), InProgressStateResult())
    }

    fun capaBlancaStateBuilder(): GameState {
        return GameState(BoardFactory().initialiceCapaBlancaBoard(), CommonTurnStrategy(Colour.WHITE), History(listOf()), InProgressStateResult())
    }

    fun emptyBoardGameStateBuilder(): GameState {
        return GameState(BoardFactory().initialiceBasicEmptyBoard(), CommonTurnStrategy(Colour.WHITE), History(listOf()), InProgressStateResult())
    }

}