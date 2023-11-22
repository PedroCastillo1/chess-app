package game.chess.validator

import adt.InvalidMovementResult
import adt.ResultMovement
import adt.ValidMovementResult
import game.common.GameState
import game.common.movement.Movement
import game.common.board.Position
import game.common.piece.Colour
import game.common.piece.Colour.*
import game.common.factory.BoardFactory
import game.common.validator.MovementValidator


class NotInCheckMovementValidator : MovementValidator {

    val boardFactory = BoardFactory()

    override fun validate(movement: Movement, gameState: GameState): ResultMovement {

        when(gameState.getCurrentColour()){
            WHITE -> {
                val designatedPosition = gameState.getPositionByPieceID("KW")!!
                val auxNewGameState = gameState.copy(board = boardFactory.boardFromReference(gameState.board, movement), gameState.changeColourTurn())
                if(designatedPosition == movement.from) {
                    if (isPieceColourTargetingPosition(movement.to, auxNewGameState, BLACK)) {
                        return InvalidMovementResult("King is left in check")
                    }
                } else if (isPieceColourTargetingPosition(designatedPosition, auxNewGameState, BLACK)) {
                    return InvalidMovementResult("King is left in check")
                }
                return ValidMovementResult()
            }
            BLACK -> {
                val designatedPosition = gameState.getPositionByPieceID("KB")!!
                val auxNewGameState = gameState.copy(board = boardFactory.boardFromReference(gameState.board, movement), gameState.changeColourTurn())
                if(designatedPosition == movement.from) {
                    if (isPieceColourTargetingPosition(movement.to, auxNewGameState, WHITE)) {
                        return InvalidMovementResult("King is left in check")
                    }
                } else if (isPieceColourTargetingPosition(designatedPosition, auxNewGameState, WHITE)) {
                    return InvalidMovementResult("King is left in check")
                }
                return ValidMovementResult()
            }
        }
    }

    private fun isPieceColourTargetingPosition(targetPosition: Position, gameState: GameState, colour: Colour): Boolean{
        val pieceFilter = gameState.getPieceMap().values.filter { it.colour == colour && it.id != "KW" && it.id != "KB" }
        for(piece in pieceFilter){
            val originPostion = gameState.getPositionByPieceID(piece.id)!!
            if(piece.mv.validate(Movement(targetPosition, originPostion), gameState) is ValidMovementResult){
                return true;
            }
        }
        return false;
    }

}