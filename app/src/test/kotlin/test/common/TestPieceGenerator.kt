package test.common

import game.common.GameState
import game.common.piece.Colour
import game.common.factory.GameStateFactory
import game.common.factory.PieceFactory
import game.common.board.Position
import game.common.piece.Piece

class TestPieceGenerator {
    fun generateCenter(piece: Piece) : GameState{
        var gameState = GameStateFactory().emptyBoardGameStateBuilder()
        val pieceMap = mapOf((Position(4, 4) to piece))
        return gameState.copy(board = gameState.board.copy(pieceMap = pieceMap))
    }

    fun generateWithEnemy(piece: Piece) : GameState {
        val enemyColour = when(piece.colour) {
            Colour.WHITE -> Colour.BLACK
            Colour.BLACK -> Colour.WHITE
        }
        var gameState = GameStateFactory().emptyBoardGameStateBuilder()
        val pieceMap = mapOf(
            (Position(4, 4) to piece),
            (Position(4, 5) to PieceFactory().pawnFactory("EP1", enemyColour)),
            (Position(4, 3) to PieceFactory().pawnFactory("EP2", enemyColour)),
            (Position(3, 5) to PieceFactory().pawnFactory("EP3", enemyColour)),
            (Position(3, 4) to PieceFactory().pawnFactory("EP4", enemyColour)),
            (Position(3, 3) to PieceFactory().pawnFactory("EP5", enemyColour)),
            (Position(5, 5) to PieceFactory().pawnFactory("EP6", enemyColour)),
            (Position(5, 4) to PieceFactory().pawnFactory("EP7", enemyColour)),
            (Position(5, 3) to PieceFactory().pawnFactory("EP8", enemyColour)),
            )
        return gameState.copy(board = gameState.board.copy(pieceMap = pieceMap))
    }

    fun generateWithEnemyChecker(piece : Piece) : GameState {
        var gameState = GameStateFactory().emptyBoardGameStateBuilder()
        val pieceMap = mapOf(
            (Position(4, 4) to piece),
            (Position(5, 5) to PieceFactory().checkerFactory("EP1", Colour.BLACK)),
        )
        return gameState.copy(board = gameState.board.copy(pieceMap = pieceMap))
    }

    fun generateWithAlly(piece: Piece) : GameState {
        val allyColour = piece.colour
        var gameState = GameStateFactory().emptyBoardGameStateBuilder()
        val pieceMap = mapOf(
            (Position(4, 4) to piece),
            (Position(4, 5) to PieceFactory().pawnFactory("EP1", allyColour)),
            (Position(4, 3) to PieceFactory().pawnFactory("EP2", allyColour)),
            (Position(3, 5) to PieceFactory().pawnFactory("EP3", allyColour)),
            (Position(3, 4) to PieceFactory().pawnFactory("EP4", allyColour)),
            (Position(3, 3) to PieceFactory().pawnFactory("EP5", allyColour)),
            (Position(5, 5) to PieceFactory().pawnFactory("EP6", allyColour)),
            (Position(5, 4) to PieceFactory().pawnFactory("EP7", allyColour)),
            (Position(5, 3) to PieceFactory().pawnFactory("EP8", allyColour)),
        )
        return gameState.copy(board = gameState.board.copy(pieceMap = pieceMap))
    }


    fun generateEmpty(): GameState{
        return GameStateFactory().emptyBoardGameStateBuilder()
    }
}