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

class CrownedTest {

    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test crowned checker movement successful with valid pattern`() {
        val crownedChecker = PieceFactory().crownedFactory("CC1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(crownedChecker)

        // Valid movements
        val movement1 = Movement(Position(5, 5), Position(4, 4))
        val movement2 = Movement(Position(3, 5), Position(4, 4))

        assertTrue(crownedChecker.mv.validate(movement1, gameState) is ValidMovementResult)
        assertTrue(crownedChecker.mv.validate(movement2, gameState) is ValidMovementResult)
    }

    @Test
    fun `test crowned checker capture successful`() {
        val crownedChecker = PieceFactory().crownedFactory("CC1", Colour.WHITE)
        val gameState = gameGenerator.generateWithEnemy(crownedChecker)

        // Capture movement
        val captureMovement = Movement(Position(6, 6), Position(4, 4))

        assertTrue(crownedChecker.mv.validate(captureMovement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test crowned checker capture unsuccessful with no opponent piece`() {
        val crownedChecker = PieceFactory().crownedFactory("CC1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(crownedChecker)

        // Attempt to capture without an opponent piece
        val captureMovement = Movement(Position(6, 6), Position(4, 4))

        assertTrue(crownedChecker.mv.validate(captureMovement, gameState) is InvalidMovementResult)
    }
}
