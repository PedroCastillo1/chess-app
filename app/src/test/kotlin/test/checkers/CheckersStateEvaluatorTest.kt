package test.checkers

import adt.InProgressStateResult
import game.common.factory.GameStateFactory
import adt.WinStateResult
import game.checkers.state.CheckersStateEvaluator
import game.common.board.Board
import game.common.piece.Colour
import game.common.factory.PieceFactory
import game.common.board.Position
import kotlin.test.Test
import kotlin.test.assertTrue


class CheckersStateEvaluatorTest {

    @Test
    fun `test in progress`() {
        val stateEvaluator = CheckersStateEvaluator()
        val initialGs = GameStateFactory().checkersStateBuilder()

        assertTrue(stateEvaluator.validate(initialGs) is InProgressStateResult)
    }

    @Test
    fun `test checkmate`() {
        val stateEvaluator = CheckersStateEvaluator()
        val initGs = GameStateFactory().checkersStateBuilder().copy(board = generateCheckMateBoard())
        assertTrue(stateEvaluator.validate(initGs) is WinStateResult)
    }

    private fun generateCheckMateBoard(): Board {
        val pieceFactory = PieceFactory()
        val pieceMap = mapOf(
            (Position(8, 1) to pieceFactory.crownedFactory("CW1", Colour.WHITE)),
            (Position(7, 1) to pieceFactory.checkerFactory("CW2", Colour.WHITE)),
            (Position(5, 1) to pieceFactory.checkerFactory("CW3", Colour.WHITE)),
        )
        return Board(pieceMap, 8, 8)

    }

}