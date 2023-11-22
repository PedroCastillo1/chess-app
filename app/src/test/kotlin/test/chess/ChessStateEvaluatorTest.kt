package test.chess

import adt.InProgressStateResult
import game.common.factory.GameStateFactory
import adt.WinStateResult
import game.chess.state.ChessStateEvaluator
import game.common.board.Board
import game.common.piece.Colour
import game.common.factory.PieceFactory
import game.common.board.Position
import kotlin.test.Test
import kotlin.test.assertTrue

class ChessStateEvaluatorTest {

    @Test
    fun `test in progress`() {
        val stateEvaluator = ChessStateEvaluator()
        val initialGs = GameStateFactory().normalGameStateBuilder()

        assertTrue(stateEvaluator.validate(initialGs) is InProgressStateResult)
    }

    @Test
    fun `test checkmate`() {
        val stateEvaluator = ChessStateEvaluator()
        val initGs = GameStateFactory().normalGameStateBuilder().copy(board = generateCheckMateBoard())
        assertTrue(stateEvaluator.validate(initGs) is WinStateResult)
    }

    private fun generateCheckMateBoard(): Board {
        val pieceFactory = PieceFactory()
        val pieceMap = mapOf(
            (Position(8, 1) to pieceFactory.rookFactory("RW1", Colour.WHITE)),
            (Position(7, 1) to pieceFactory.queenFactory("QW", Colour.WHITE)),
            (Position(5, 1) to pieceFactory.kingFactory("KW", Colour.WHITE)),

            (Position(8, 8) to pieceFactory.kingFactory("KB", Colour.BLACK)),
        )
        return Board(pieceMap, 8, 8)

    }

}