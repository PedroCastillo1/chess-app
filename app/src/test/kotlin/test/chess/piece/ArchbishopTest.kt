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

class ArchbishopTest {

    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test archbishop movement successful with combined pattern`() {
        val archbishop = PieceFactory().archbishopFactory("A1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(archbishop)

        // Combined movements (bishop diagonal + knight L-shaped)
        val movement1 = Movement(Position(6, 6), Position(4, 4))
        val movement2 = Movement(Position(6, 2), Position(4, 4))
        val movement3 = Movement(Position(2, 6), Position(4, 4))
        val movement4 = Movement(Position(2, 2), Position(4, 4))

        assertTrue(archbishop.mv.validate(movement1, gameState) is ValidMovementResult)
        assertTrue(archbishop.mv.validate(movement2, gameState) is ValidMovementResult)
        assertTrue(archbishop.mv.validate(movement3, gameState) is ValidMovementResult)
        assertTrue(archbishop.mv.validate(movement4, gameState) is ValidMovementResult)
    }

    @Test
    fun `test archbishop movement unsuccessful with invalid pattern`() {
        val archbishop = PieceFactory().archbishopFactory("A1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(archbishop)

        // Invalid movements
        val movement1 = Movement(Position(4, 6), Position(4, 4))
        val movement2 = Movement(Position(6, 4), Position(4, 4))

        assertTrue(archbishop.mv.validate(movement1, gameState) is InvalidMovementResult)
        assertTrue(archbishop.mv.validate(movement2, gameState) is InvalidMovementResult)
    }

    @Test
    fun `test archbishop movement with obstacles`() {
        val archbishop = PieceFactory().archbishopFactory("A1", Colour.WHITE)
        val gameState = gameGenerator.generateWithAlly(archbishop)

        val movement = Movement(Position(6, 6), Position(4, 4))

        assertTrue(archbishop.mv.validate(movement, gameState) is InvalidMovementResult)
    }
}
