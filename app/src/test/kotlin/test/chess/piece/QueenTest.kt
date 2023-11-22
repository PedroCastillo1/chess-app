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

class QueenTest {

    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test diagonal forward-right successful queen movement with no obstacles`() {
        val queen = PieceFactory().queenFactory("Q1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(queen)
        val movement = Movement(Position(7, 7), Position(4, 4))
        assertTrue(queen.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test diagonal forward-left successful queen movement with no obstacles`() {
        val queen = PieceFactory().queenFactory("Q1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(queen)
        val movement = Movement(Position(1, 7), Position(4, 4))
        assertTrue(queen.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test diagonal backward-right successful queen movement with no obstacles`() {
        val queen = PieceFactory().queenFactory("Q1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(queen)
        val movement = Movement(Position(7, 1), Position(4, 4))
        assertTrue(queen.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test diagonal backward-left successful queen movement with no obstacles`() {
        val queen = PieceFactory().queenFactory("Q1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(queen)
        val movement = Movement(Position(1, 1), Position(4, 4))
        assertTrue(queen.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test vertical forward successful queen movement with no obstacles`() {
        val queen = PieceFactory().queenFactory("Q1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(queen)
        val movement = Movement(Position(4, 7), Position(4, 4))
        assertTrue(queen.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test horizontal right successful queen movement with no obstacles`() {
        val queen = PieceFactory().queenFactory("Q1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(queen)
        val movement = Movement(Position(7, 4), Position(4, 4))
        assertTrue(queen.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test horizontal left successful queen movement with no obstacles`() {
        val queen = PieceFactory().queenFactory("Q1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(queen)
        val movement = Movement(Position(1, 4), Position(4, 4))
        assertTrue(queen.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test vertical backward successful queen movement with no obstacles`() {
        val queen = PieceFactory().queenFactory("Q1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(queen)
        val movement = Movement(Position(4, 1), Position(4, 4))
        assertTrue(queen.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test queen movement with obstacles`() {
        val queen = PieceFactory().queenFactory("Q1", Colour.WHITE)
        val gameState = gameGenerator.generateWithAlly(queen)
        val movement = Movement(Position(7, 7), Position(4, 4))
        assertTrue(queen.mv.validate(movement, gameState) is InvalidMovementResult)
    }
}
