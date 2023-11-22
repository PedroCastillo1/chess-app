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

class KnightTest {

    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test knight movement successful with L-shaped pattern`() {
        val knight = PieceFactory().knightFactory("N1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(knight)

        // L-shaped movements
        val movement1 = Movement(Position(2, 3), Position(4, 4))
        val movement2 = Movement(Position(2, 5), Position(4, 4))
        val movement3 = Movement(Position(3, 2), Position(4, 4))
        val movement4 = Movement(Position(3, 6), Position(4, 4))

        assertTrue(knight.mv.validate(movement1, gameState) is ValidMovementResult)
        assertTrue(knight.mv.validate(movement2, gameState) is ValidMovementResult)
        assertTrue(knight.mv.validate(movement3, gameState) is ValidMovementResult)
        assertTrue(knight.mv.validate(movement4, gameState) is ValidMovementResult)
    }

    @Test
    fun `test knight movement unsuccessful with invalid pattern`() {
        val knight = PieceFactory().knightFactory("N1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(knight)

        // Invalid movements
        val movement1 = Movement(Position(4, 6), Position(4, 4))
        val movement2 = Movement(Position(6, 6), Position(4, 4))

        assertTrue(knight.mv.validate(movement1, gameState) is InvalidMovementResult)
        assertTrue(knight.mv.validate(movement2, gameState) is InvalidMovementResult)
    }

    @Test
    fun `test knight movement with obstacles`() {
        val knight = PieceFactory().knightFactory("N1", Colour.WHITE)
        val gameState = gameGenerator.generateWithEnemy(knight)

        // Attempting to jump over an obstacle
        val movement = Movement(Position(2, 3), Position(4, 4))

        assertTrue(knight.mv.validate(movement, gameState) is ValidMovementResult)
    }
}

