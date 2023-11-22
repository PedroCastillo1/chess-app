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

class ChancellorTest {

    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test chancellor movement successful with combined pattern`() {
        val chancellor = PieceFactory().chancellorFactory("C1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(chancellor)

        // Combined movements (rook horizontal/vertical + knight L-shaped)
        val movement1 = Movement(Position(6, 4), Position(4, 4))
        val movement2 = Movement(Position(4, 6), Position(4, 4))
        val movement3 = Movement(Position(2, 4), Position(4, 4))
        val movement4 = Movement(Position(4, 2), Position(4, 4))

        assertTrue(chancellor.mv.validate(movement1, gameState) is ValidMovementResult)
        assertTrue(chancellor.mv.validate(movement2, gameState) is ValidMovementResult)
        assertTrue(chancellor.mv.validate(movement3, gameState) is ValidMovementResult)
        assertTrue(chancellor.mv.validate(movement4, gameState) is ValidMovementResult)
    }

    @Test
    fun `test chancellor movement unsuccessful with invalid pattern`() {
        val chancellor = PieceFactory().chancellorFactory("C1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(chancellor)

        // Invalid movements
        val movement1 = Movement(Position(6, 6), Position(4, 4))
        val movement2 = Movement(Position(1, 5), Position(4, 4))

        assertTrue(chancellor.mv.validate(movement1, gameState) is InvalidMovementResult)
        assertTrue(chancellor.mv.validate(movement2, gameState) is InvalidMovementResult)
    }

    @Test
    fun `test chancellor movement with obstacles`() {
        val chancellor = PieceFactory().chancellorFactory("C1", Colour.WHITE)
        val gameState = gameGenerator.generateWithAlly(chancellor)

        val movement = Movement(Position(6, 4), Position(4, 4))

        assertTrue(chancellor.mv.validate(movement, gameState) is InvalidMovementResult)
    }
}
