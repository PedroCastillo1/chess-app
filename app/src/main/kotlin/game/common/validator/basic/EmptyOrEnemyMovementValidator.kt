package game.common.validator.basic

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.MovementValidator


class EmptyOrEnemyMovementValidator : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if(!gameState.getPieceMap().containsKey(movement.to)){
            return ValidMovementResult()
        }else{
            if(gameState.getPieceMap().get(movement.to)!!.colour != gameState.getCurrentColour()){
                return ValidMovementResult()
            }else{
                return InvalidMovementResult("To Position is occupied by a piece of the same colour")
            }
        }
    }

}