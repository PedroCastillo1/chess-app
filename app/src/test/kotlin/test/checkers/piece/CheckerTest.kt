package test.checkers.piece

import adt.InvalidMovementResult
import adt.ValidMovementResult
import game.common.piece.Colour
import game.common.factory.PieceFactory
import game.common.movement.Movement
import game.common.board.Position
import test.common.TestPieceGenerator
import kotlin.test.Test
import kotlin.test.assertTrue

class CheckerTest {

    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test checker movement succesfull with valid pattern`() {
        val checker = PieceFactory().checkerFactory("C1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(checker)

        // Invalid movements
        val movement1 = Movement(Position(5, 5), Position(4, 4))
        val movement2 = Movement(Position(3, 5), Position(4, 4))

        assertTrue(checker.mv.validate(movement1, gameState) is ValidMovementResult)
        assertTrue(checker.mv.validate(movement2, gameState) is ValidMovementResult)
    }

    @Test
    fun `test checker capture successful`() {
        val checker = PieceFactory().checkerFactory("C1", Colour.WHITE)
        val gameState = gameGenerator.generateWithEnemy(checker)

        // Capture movement
        val captureMovement = Movement(Position(6, 6), Position(4, 4))

        assertTrue(checker.mv.validate(captureMovement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test checker capture unsuccessful with no opponent piece`() {
        val checker = PieceFactory().checkerFactory("C1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(checker)

        // Attempt to capture without an opponent piece
        val captureMovement = Movement(Position(6, 6), Position(4, 4))

        assertTrue(checker.mv.validate(captureMovement, gameState) is InvalidMovementResult)
    }
}
