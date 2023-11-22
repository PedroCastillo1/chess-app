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


class RookTest {

    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test vertical forward successful rook movement with no obstacles`() {
        val rook = PieceFactory().rookFactory("R1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(rook)
        val movement = Movement(Position(4, 1), Position(4, 4))
        assertTrue(rook.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test horizontal right successful rook movement with no obstacles`() {
        val rook = PieceFactory().rookFactory("R1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(rook)
        val movement = Movement(Position(8, 4), Position(4, 4))
        assertTrue(rook.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test horizontal left successful rook movement with no obstacles`() {
        val rook = PieceFactory().rookFactory("R1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(rook)
        val movement = Movement(Position(1, 4), Position(4, 4))
        assertTrue(rook.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test vertical backward successful rook movement with no obstacles`() {
        val rook = PieceFactory().rookFactory("R1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(rook)
        val movement = Movement(Position(4, 8), Position(4, 4))
        assertTrue(rook.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test rook movement with obstacles`() {
        val rook = PieceFactory().rookFactory("R1", Colour.WHITE)
        val gameState = gameGenerator.generateWithAlly(rook)
        val movement = Movement(Position(4, 1), Position(4, 4))
        assertTrue(rook.mv.validate(movement, gameState) is InvalidMovementResult)
    }
}
