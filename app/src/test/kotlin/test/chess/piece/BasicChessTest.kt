package test.chess.piece

import adt.InvalidMovementResult
import game.chess.validator.BasicChessMovementValidator
import game.common.piece.Colour
import game.common.factory.PieceFactory
import game.common.movement.Movement
import game.common.board.Position
import test.common.TestPieceGenerator
import kotlin.test.Test
import kotlin.test.assertTrue


class BasicChessTest {
    val gameGenerator = TestPieceGenerator()

    @Test
    fun `test basic movement same position`() {
        val mv = BasicChessMovementValidator()
        val archbishop = PieceFactory().archbishopFactory("A1", Colour.WHITE)
        val gameState = gameGenerator.generateCenter(archbishop)

        val movement = Movement(Position(4, 4), Position(4, 4))

        assertTrue(mv.validate(movement, gameState) is InvalidMovementResult)
    }

    @Test
    fun `test basic movement with ally in position`() {
        val mv = BasicChessMovementValidator()
        val archbishop = PieceFactory().archbishopFactory("A1", Colour.WHITE)
        val gameState = gameGenerator.generateWithAlly(archbishop)

        val movement = Movement(Position(4, 5), Position(4, 4))

        assertTrue(mv.validate(movement, gameState) is InvalidMovementResult)
    }
    @Test
    fun `test basic movement with wrong colour`() {
        val mv = BasicChessMovementValidator()
        val archbishop = PieceFactory().archbishopFactory("A1", Colour.BLACK)
        val gameState = gameGenerator.generateCenter(archbishop)

        val movement = Movement(Position(4, 5), Position(4, 4))

        assertTrue(mv.validate(movement, gameState) is InvalidMovementResult)
    }




}