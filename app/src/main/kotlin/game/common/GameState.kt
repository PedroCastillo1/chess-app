package game.common

import adt.StateResult
import adt.ValidMovementResult
import game.common.board.Board
import game.common.turn.TurnStrategy
import game.common.piece.Piece
import game.checkers.validator.BasicCheckersValidator
import game.chess.validator.BasicChessMovementValidator
import game.common.history.History
import game.common.piece.Colour
import game.common.movement.Movement
import game.common.board.Position

data class GameState(
    val board: Board,
    val currColour: TurnStrategy,
    val history: History,
    val state: StateResult
) {

    fun getPieceMap(): Map<Position, Piece> {
        return board.pieceMap
    }

    fun getPiece(position: Position): Piece {
        return getPieceMap().get(position)!!
    }

    fun getPositionByPieceID(id: String): Position {
        return board.pieceMap.entries.find { it.value.id == id }!!.key
    }


    fun chessPieceHasAnyValidMovement(piece: Piece): Boolean {
        val fromPosition = board.pieceMap.filterKeys { it == getPositionByPieceID(piece.id) }.keys.first()
        for (i in 1..board.numCol) {
            for (j in 1..board.numRow) {
                val toPosition = Position(i, j)
                val auxMovement = Movement(toPosition, fromPosition)
                if (BasicChessMovementValidator().validate(auxMovement, gameState = this) is ValidMovementResult) {
                    if (piece.mv.validate(auxMovement, gameState = this) is ValidMovementResult) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun checkersPieceHasAnyValidMovement(piece: Piece): Boolean {
        val fromPosition = board.pieceMap.filterKeys { it == getPositionByPieceID(piece.id) }.keys.first()
        for (i in 1..board.numCol) {
            for (j in 1..board.numRow) {
                val toPosition = Position(i, j)
                val auxMovement = Movement(toPosition, fromPosition)
                if (BasicCheckersValidator().validate(auxMovement, gameState = this) is ValidMovementResult) {
                    if (piece.mv.validate(auxMovement, gameState = this) is ValidMovementResult) {
                        return true
                    }
                }
            }
        }
        return false
    }


    fun positionsThatThreatenKing(colour: Colour): List<Position> {
        var listOfPosition = mutableListOf<Position>()
        val kingPosition = board.pieceMap.entries.find { it.value.type == "KING" && it.value.colour == colour }!!.key
        val enemyPieces = board.pieceMap.entries.filter { it.value.colour !== colour }
        for (enemyPiece in enemyPieces) {
            val enemyPiecePosition = enemyPiece.key
            val enemyPieceMovement = Movement(kingPosition, enemyPiecePosition)
            if (enemyPiece.value.mv.validate(enemyPieceMovement, gameState = this) is ValidMovementResult) {
                listOfPosition.add(enemyPiecePosition)
            }
        }
        val immutableList = listOfPosition.toList()
        return immutableList
    }

    fun isKingThreaten(colour: Colour): Boolean {
        return positionsThatThreatenKing(colour).isNotEmpty()
    }

    fun getAllPiecesOfColour(colour: Colour): List<Piece> {
        return board.pieceMap.entries.filter { it.value.colour == colour }.map { it.value }
    }

    fun isPositionThreaten(position: Position): Boolean{
        val enemyPieces = board.pieceMap.entries.filter { it.value.colour !== getCurrentColour() && it.value.type != "KING"}
        for (enemyPiece in enemyPieces) {
            val enemyPiecePosition = enemyPiece.key
            val enemyPieceMovement = Movement(position, enemyPiecePosition)
            if (enemyPiece.value.mv.validate(enemyPieceMovement, gameState = this) is ValidMovementResult) {
                return true
            }
        }
        return false
    }


    fun getBoardsHistory(): List<Board> {
        return history.boardHistory
    }

    fun getCurrentColour(): Colour {
        return currColour.getCurrentColour()
    }

    fun changeColourTurn(): TurnStrategy {
        return currColour.advanceTurn()
    }

    fun getNextColour(): Colour {
        return currColour.advanceTurn().getCurrentColour()
    }

}
