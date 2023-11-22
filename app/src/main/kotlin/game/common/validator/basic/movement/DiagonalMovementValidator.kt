package game.common.validator.basic.movement

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator
import kotlin.math.abs


class DiagonalMovementValidator : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        val auxX = (movement.to.column - movement.from.column)
        val auxY = (movement.to.row - movement.from.row)
        if(abs(auxX) == abs(auxY)){
            return ValidMovementResult()
        }
        return InvalidMovementResult("Piece is not moving correctly")
    }
}