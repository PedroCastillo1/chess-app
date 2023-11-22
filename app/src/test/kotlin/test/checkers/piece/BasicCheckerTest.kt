package test.checkers.piece

import adt.InvalidMovementResult
import game.checkers.validator.BasicCheckersValidator
import game.common.piece.Colour
import game.common.factory.PieceFactory
import game.common.movement.Movement
import game.common.board.Position
import test.common.TestPieceGenerator
import kotlin.test.Test
import kotlin.test.assertTrue


class BasicCheckerTest {
    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test basic movement same position`() {
        val mv = BasicCheckersValidator()
        val archbishop = PieceFactory().archbishopFactory("A1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(archbishop)

        val movement = Movement(Position(4, 4), Position(4, 4))

        assertTrue(mv.validate(movement, gameState) is InvalidMovementResult)
    }

    @Test
    fun `test basic movement with ally in position`() {
        val mv = BasicCheckersValidator()
        val archbishop = PieceFactory().archbishopFactory("A1", Colour.WHITE)
        val gameState = gameGenerator.generateWithAlly(archbishop)

        val movement = Movement(Position(4, 5), Position(4, 4))

        assertTrue(mv.validate(movement, gameState) is InvalidMovementResult)
    }
    @Test
    fun `test basic movement with wrong colour`() {
        val mv = BasicCheckersValidator()
        val archbishop = PieceFactory().archbishopFactory("A1", Colour.BLACK)
        val gameState = gameGenerator.generateCenter(archbishop)

        val movement = Movement(Position(4, 5), Position(4, 4))

        assertTrue(mv.validate(movement, gameState) is InvalidMovementResult)
    }




}