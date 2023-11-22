package test.checkers.mover

import game.common.factory.GameStateFactory
import game.common.piece.Colour
import game.common.factory.PieceFactory
import game.common.movement.Movement
import game.common.board.Position
import test.common.TestPieceGenerator
import kotlin.test.Test
import kotlin.test.assertEquals

class CheckerMBTest {
    val gameStateFactory = GameStateFactory()

    @Test
    fun `test checker movement behaviour`() {
        val initialGs = gameStateFactory.checkersStateBuilder()
        val pieceToMove = initialGs.getPieceMap()[Position(5, 3)]!!
        val movement = Movement(Position(4, 4), Position(5, 3))
        val finalGs = pieceToMove.mb.move(initialGs, movement)

        val expectedBoard = initialGs.getPieceMap().toMutableMap()
        expectedBoard.remove(Position(5, 3))
        expectedBoard[Position(4, 4)] = pieceToMove
        val expectedGs = initialGs.copy(board = initialGs.board.copy(pieceMap = expectedBoard))
        assertEquals(expectedGs, finalGs)
    }

    @Test
    fun `test checker eat movement behaviour`() {
        val pieceToMove = PieceFactory().checkerFactory("C1", Colour.WHITE)
        val initialGs = TestPieceGenerator().generateWithEnemyChecker(pieceToMove)
        val movement = Movement(Position(6, 6), Position(4, 4))
        val finalGs = pieceToMove.mb.move(initialGs, movement)

        val expectedBoard = initialGs.getPieceMap().toMutableMap()
        expectedBoard.remove(Position(4, 4))
        expectedBoard.remove(Position(5, 5))
        expectedBoard[Position(6    , 6)] = pieceToMove

        val expectedGs = initialGs.copy(
            board = initialGs.board.copy(pieceMap = expectedBoard.toMap())
        )

        assertEquals(expectedGs, finalGs)
    }


}