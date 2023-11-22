package game.common.validator.basic

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.board.Position
import game.common.validator.MovementValidator
import game.common.validator.basic.movement.DiagonalMovementValidator
import game.common.validator.basic.movement.HorizontalMovementValidator
import game.common.validator.basic.movement.VerticalMovementValidator
import kotlin.math.abs


class PathClearValidator() : MovementValidator {
    val diagonalMovementValidator = DiagonalMovementValidator()
    val verticalMovementValidator = VerticalMovementValidator()
    val horizontalMovementValidator = HorizontalMovementValidator()
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if (diagonalMovementValidator.validate(movement, gameState) is ValidMovementResult) {
            return diagonalPathMovementValidator(movement.copy(), gameState)
        }

        if (horizontalMovementValidator.validate(movement, gameState) is ValidMovementResult) {
            return horizontalPathMovementValidator(movement, gameState)
        }

        if (verticalMovementValidator.validate(movement, gameState) is ValidMovementResult) {
            return verticalPathMovementValidator(movement, gameState)
        }
        return InvalidMovementResult("Move is not valid")
    }

    private fun diagonalPathMovementValidator(movement: Movement, gameState: GameState): ResultMovement {
        val auxX = (movement.to.column - movement.from.column)
        val auxY = (movement.to.row - movement.from.row)

        val stepX = if (auxX > 0) 1 else -1
        val stepY = if (auxY > 0) 1 else -1

        for (i in 1 until abs(auxX)) {
            val intermediatePosition = Position(
                movement.from.column + i * stepX,
                movement.from.row + i * stepY
            )

            if (gameState.getPieceMap().containsKey(intermediatePosition)) {
                return InvalidMovementResult("A intermediate position is blocked")
            }
        }

        return ValidMovementResult()
    }

    private fun horizontalPathMovementValidator(movement: Movement, gameState: GameState): ResultMovement {
        val aux = abs(movement.from.column - movement.to.column)
        for (i in 1 until aux) {
            val intermediatePosition = Position(
                movement.from.column + i,
                movement.from.row,
            )
            if (gameState.getPieceMap().contains(intermediatePosition)) {
                return InvalidMovementResult("A intermediate position is blocked")
            }
        }
        return ValidMovementResult()
    }

    private fun verticalPathMovementValidator(movement: Movement, gameState: GameState): ResultMovement {
        val fromRow = movement.from.row
        val toRow = movement.to.row
        val rowDifference = abs(fromRow - toRow)
        val step = if (fromRow < toRow) 1 else -1 // Determina si el movimiento es hacia arriba o hacia abajo

        for (i in 1 until rowDifference) {
            val intermediatePosition = Position(
                movement.from.column,
                fromRow + i * step
            )

            if (gameState.getPieceMap().contains(intermediatePosition)) {
                return InvalidMovementResult("An intermediate position is blocked")
            }
        }
        return ValidMovementResult()
    }



}