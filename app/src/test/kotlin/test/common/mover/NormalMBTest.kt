package test.common.mover

import game.common.factory.GameStateFactory
import game.common.piece.Colour
import game.common.factory.PieceFactory
import game.common.movement.Movement
import game.common.board.Position
import test.common.TestPieceGenerator
import kotlin.test.Test
import kotlin.test.assertEquals


class NormalMBTest {
    val gameStateFactory = GameStateFactory()

    @Test
    fun `test checker eat movement behaviour`() {
        val pieceToMove = PieceFactory().queenFactory("Q1", Colour.WHITE)
        val initialGs = TestPieceGenerator().generateCenter(pieceToMove)
        val movement = Movement(Position(6, 6), Position(4, 4))
        val finalGs = pieceToMove.mb.move(initialGs, movement)

        val expectedBoard = initialGs.getPieceMap().toMutableMap()
        expectedBoard.remove(Position(4, 4))
        expectedBoard.remove(Position(5, 5))
        expectedBoard[Position(6, 6)] = pieceToMove

        val expectedGs = initialGs.copy(
            board = initialGs.board.copy(pieceMap = expectedBoard.toMap())
        )

        assertEquals(expectedGs, finalGs)
    }

}