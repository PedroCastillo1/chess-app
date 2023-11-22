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


class PawnTest {

    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test one step foward succesfull pawn movement with no obstacles`() {
        val pawn = PieceFactory().pawnFactory("P1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(pawn)
        val movement = Movement(Position(4, 5), Position(4, 4))
        assertTrue(pawn.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test double step foward succesfull pawn movement with no obstacles`() {
        val pawn = PieceFactory().pawnFactory("P1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(pawn)
        val movement = Movement(Position(4, 6), Position(4, 4))
        assertTrue(pawn.mv.validate(movement, gameState) is ValidMovementResult)
    }

    @Test
    fun `test one step diagonal succesfull pawn movement with enemy`() {
        val pawn = PieceFactory().pawnFactory("P1", Colour.WHITE)
        val gameState = gameGenerator.generateWithEnemy(pawn)
        val movementRight = Movement(Position(5, 5), Position(4, 4))
        val movementLeft = Movement(Position(3, 5), Position(4, 4))
        assertTrue(pawn.mv.validate(movementLeft, gameState) is ValidMovementResult)
        assertTrue(pawn.mv.validate(movementRight, gameState) is ValidMovementResult)
    }

    @Test
    fun `test one step foward unsuccesfull pawn movement with obstacle`() {
        val pawn = PieceFactory().pawnFactory("P1", Colour.WHITE)
        val gameState = gameGenerator.generateWithAlly(pawn)
        val movement = Movement(Position(4, 5), Position(4, 4))
        assertTrue(pawn.mv.validate(movement, gameState) is InvalidMovementResult)
    }
}