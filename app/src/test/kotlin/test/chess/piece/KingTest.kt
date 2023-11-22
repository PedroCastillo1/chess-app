package test.chess.piece

import adt.InvalidMovementResult
import adt.ValidMovementResult
import game.common.piece.Colour
import game.common.factory.PieceFactory
import game.common.movement.Movement
import game.common.board.Position
import test.common.TestPieceGenerator
import kotlin.test.Test
import kotlin.test.assertTrue


class KingTest {

    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test king movement successful within one square`() {
        val king = PieceFactory().kingFactory("K1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(king)

        val movement1 = Movement(Position(4, 5), Position(4, 4))
        val movement2 = Movement(Position(5, 5), Position(4, 4))
        val movement3 = Movement(Position(5, 4), Position(4, 4))

        assertTrue(king.mv.validate(movement1, gameState) is ValidMovementResult)
        assertTrue(king.mv.validate(movement2, gameState) is ValidMovementResult)
        assertTrue(king.mv.validate(movement3, gameState) is ValidMovementResult)
    }

    @Test
    fun `test king movement unsuccessful beyond one square`() {
        val king = PieceFactory().kingFactory("K1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(king)

        // Movements beyond one square
        val movement1 = Movement(Position(4, 6), Position(4, 4))
        val movement2 = Movement(Position(6, 6), Position(4, 4))

        assertTrue(king.mv.validate(movement1, gameState) is InvalidMovementResult)
        assertTrue(king.mv.validate(movement2, gameState) is InvalidMovementResult)
    }

}
