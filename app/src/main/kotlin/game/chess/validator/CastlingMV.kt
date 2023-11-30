package game.chess.validator

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.board.Position
import game.common.piece.Colour
import game.common.validator.MovementValidator
import game.common.validator.basic.MaxMovementCount
import game.common.validator.basic.PathClearValidator
import kotlin.math.abs

class CastlingMV() : MovementValidator {

    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        if (kingIsInValidPosition(movement, gameState)) {
            if (isShortCastlin(movement)) {
               return evaluateShortCastlin(movement, gameState)
            }else{
                return evaluateLongCastling(movement, gameState)
            }
        }
        return InvalidMovementResult("Invalid movement")
    }

    private fun evaluateShortCastlin(movement: Movement, gameState: GameState): ResultMovement {
        if (isRookAbleToShortCastle(movement, gameState)) {
            return ValidMovementResult()
        }
        return InvalidMovementResult("Invalid movement")
    }

    private fun isRookAbleToShortCastle(movement: Movement, gameState: GameState): Boolean {
        val kingNewPosition = Position(movement.to.column, movement.to.row)
        val rookPosition = Position(movement.to.column + 1, movement.to.row)
        val extendedMove = Movement(movement.from, rookPosition)
        return isRookOfColourInPosition(rookPosition, gameState, 2) && isPathClear(
                extendedMove,
                gameState
        ) && !(gameState.isPositionThreaten(kingNewPosition)) && rookHasNotMoved(movement, gameState)
    }

    private fun isRookAbleToLongCastle(movement: Movement, gameState: GameState): Boolean {
        val kingNewPosition = Position(movement.to.column, movement.to.row)
        val rookPosition = Position(movement.to.column - 2, movement.to.row)
        val extendedMove = Movement(movement.from, rookPosition)
        return (isRookOfColourInPosition(rookPosition, gameState, 1) && isPathClear(
                extendedMove,
                gameState
        ) && !(gameState.isPositionThreaten(kingNewPosition)) && rookHasNotMoved(movement, gameState))
    }

    private fun evaluateLongCastling(movement: Movement, gameState: GameState): ResultMovement {
        val kingNewPosition = Position(movement.to.column, movement.to.row)
        val rookPosition = Position(movement.to.column - 2, movement.to.row)
        val extendedMove = Movement(movement.from, rookPosition)
        if (isRookAbleToLongCastle(movement, gameState)) {
            return ValidMovementResult()
        }
        return InvalidMovementResult("Invalid movement")
    }

    private fun kingIsInValidPosition(movement: Movement, gameState: GameState): Boolean {
        return isKingMovingTwoSquares(movement) && isMovementInCorrectRow(movement, gameState) && kingHasNotMoved(
                movement,
                gameState
        )
    }

    private fun isPathClear(movement: Movement, gameState: GameState) : Boolean{
        return PathClearValidator().validate(movement, gameState) is ValidMovementResult
    }


    private fun isKingMovingTwoSquares(movement: Movement): Boolean {
        if (movement.from.row == movement.to.row) {
            val absDif = abs(movement.from.column - movement.to.column)
            if (absDif == 2) {
                return true
            }
        }
        return false
    }

    private fun rookHasNotMoved(movement: Movement, gameState: GameState) : Boolean{
        val num = if(isShortCastlin(movement)) {
            2
        }else{
            1
        }

        val rookId = if(gameState.getCurrentColour() == Colour.WHITE){
            "RW$num"
        }else{
            "RB$num"
        }

        return MaxMovementCount(1, rookId).validate(movement, gameState) is ValidMovementResult
    }

    private fun kingHasNotMoved(movement : Movement, gameState: GameState): Boolean {
        val kingId = generateKingId(gameState)
        return MaxMovementCount(1, kingId).validate(movement, gameState) is ValidMovementResult
    }

    private fun isMovementInCorrectRow(movement: Movement, gameState: GameState): Boolean {
        return when (gameState.getCurrentColour()) {
            Colour.WHITE -> {
                movement.from.row == 1
            }

            Colour.BLACK -> {
                movement.from.row == gameState.board.numRow
            }
        }
    }

    private fun isRookOfColourInPosition(position: Position, gameState: GameState, side: Int): Boolean {
        val idRook = if (gameState.getCurrentColour() == Colour.WHITE) {
            "RW$side"
        } else {
            "RB$side"
        }
        if(!gameState.getPieceMap().containsKey(position)){
            return false
        }else{
            return gameState.getPiece(position).id == idRook
        }
    }


    private fun generateKingId(gameState: GameState): String {
        return if (gameState.getCurrentColour() == Colour.WHITE) {
            "KW"
        } else {
            "KB"
        }
    }

    private fun isShortCastlin(movement: Movement): Boolean {
        return (movement.to.column - movement.from.column) > 0
    }

}