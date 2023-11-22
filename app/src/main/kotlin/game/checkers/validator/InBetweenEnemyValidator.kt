package game.checkers.validator

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.movement.Movement
import game.common.board.Position
import game.common.GameState
import game.common.validator.MovementValidator
import kotlin.math.abs


class InBetweenEnemyValidator : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
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
                if(gameState.getPieceMap()[intermediatePosition]!!.colour == gameState.getNextColour()){
                    return ValidMovementResult()
                }else{
                    return InvalidMovementResult("Piece is not moving correctly")
                }
            }
        }

        return InvalidMovementResult("Piece is not moving correctly")
    }
}