package game.common.validator.basic.movement

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.piece.Colour
import game.common.validator.MovementValidator


class FowardMovementValidator : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if(movement.to.column === movement.from.column){
            when(gameState.getCurrentColour()){
                Colour.WHITE -> {
                    if(movement.to.row > movement.from.row){
                        return ValidMovementResult()
                    }
                    return InvalidMovementResult("Piece is not moving correctly")
                }
                Colour.BLACK -> {
                    if(movement.to.row < movement.from.row){
                        return ValidMovementResult()
                    }
                    return InvalidMovementResult("Piece is not moving correctly")
                }
            }
        }
        return InvalidMovementResult("Piece is not moving correctly")
    }
}