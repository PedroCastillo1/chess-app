package game.chess.mover

import adt.ValidMovementResult
import game.chess.validator.CastlingMV
import game.common.GameState
import game.common.movement.Movement
import game.common.board.Position
import game.common.piece.Colour.*
import game.common.movement.MovementBehaviour
import game.common.movement.NormalMovementBehaviour


class KingMovementBehaviour : MovementBehaviour {
    override fun move(gameState: GameState, movement: Movement): GameState {
        return if (CastlingMV().validate(movement, gameState) is ValidMovementResult){
            castlingMovementBehaviour(gameState, movement)
        }else{
            NormalMovementBehaviour().move(gameState, movement)
        }
    }

    private fun castlingMovementBehaviour(gameState: GameState, movement: Movement) : GameState {
        val king = gameState.getPiece(movement.from)
        val newGameState = NormalMovementBehaviour().move(gameState, movement)

        if(isShortCastlin(movement)){
            when(gameState.getCurrentColour()){
                WHITE -> {
                    val rook = gameState.getPiece(Position(gameState.board.numCol,1))
                    return rook.mb.move(newGameState, Movement(
                        Position(movement.to.column-1, 1),
                        Position(gameState.board.numCol,1)
                    )
                    )
                }
                BLACK -> {
                    val rook = gameState.getPiece(Position(gameState.board.numCol,gameState.board.numRow))
                    return rook.mb.move(newGameState, Movement(Position(movement.to.column-1, gameState.board.numRow), Position(gameState.board.numCol,gameState.board.numRow)))
                }
            }
        }else{
            when(gameState.getCurrentColour()){
                WHITE -> {
                    val rook = gameState.getPiece(Position(1,1))
                    return rook.mb.move(newGameState, Movement(Position(movement.to.column+1, 1), Position(1,1)))
                }
                BLACK -> {
                    val rook = gameState.getPiece(Position(1,gameState.board.numRow))
                    return rook.mb.move(newGameState, Movement(Position(movement.to.column+1, gameState.board.numRow), Position(1,gameState.board.numRow)))
                }
            }
        }
    }

    private fun isShortCastlin(movement: Movement): Boolean {
        return (movement.to.column - movement.from.column) > 0
    }

}