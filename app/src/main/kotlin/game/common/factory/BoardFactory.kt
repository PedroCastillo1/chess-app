package game.common.factory

import game.common.board.Board
import game.common.movement.Movement
import game.common.piece.Colour
import game.common.board.Position

class BoardFactory {

    fun initialiceBasicEmptyBoard(): Board {
        return Board(mapOf(), 8, 8)
    }

    fun initialice10x10EmptyBoard(): Board {
        return Board(mapOf(), 10, 10)
    }

    fun boardFromReference(board: Board, movement: Movement): Board {
        val pieceToMove = board.pieceMap[movement.from]!!

        val updatedPieceMap = board.pieceMap
            .filterKeys { it != movement.from }
            .plus(movement.to to pieceToMove)

        return board.copy(pieceMap = updatedPieceMap)
    }

    fun initialiceNormalBoard(): Board {
        val pieceFactory = PieceFactory()
        val pieceMap = mapOf(
            (Position(1, 1) to pieceFactory.rookFactory("RW1", Colour.WHITE)),
            (Position(2, 1) to pieceFactory.knightFactory("KW1", Colour.WHITE)),
            (Position(3, 1) to pieceFactory.bishopFactory("BW1", Colour.WHITE)),
            (Position(4, 1) to pieceFactory.queenFactory("QW", Colour.WHITE)),
            (Position(5, 1) to pieceFactory.kingFactory("KW", Colour.WHITE)),
            (Position(6, 1) to pieceFactory.bishopFactory("BW2", Colour.WHITE)),
            (Position(7, 1) to pieceFactory.knightFactory("KW2", Colour.WHITE)),
            (Position(8, 1) to pieceFactory.rookFactory("RW2", Colour.WHITE)),

            (Position(1, 2) to pieceFactory.pawnFactory("PW1", Colour.WHITE)),
            (Position(2, 2) to pieceFactory.pawnFactory("PW2", Colour.WHITE)),
            (Position(3, 2) to pieceFactory.pawnFactory("PW3", Colour.WHITE)),
            (Position(4, 2) to pieceFactory.pawnFactory("PW4", Colour.WHITE)),
            (Position(5, 2) to pieceFactory.pawnFactory("PW5", Colour.WHITE)),
            (Position(6, 2) to pieceFactory.pawnFactory("PW6", Colour.WHITE)),
            (Position(7, 2) to pieceFactory.pawnFactory("PW7", Colour.WHITE)),
            (Position(8, 2) to pieceFactory.pawnFactory("PW8", Colour.WHITE)),

            (Position(1, 7) to pieceFactory.pawnFactory("PB1", Colour.BLACK)),
            (Position(2, 7) to pieceFactory.pawnFactory("PB2", Colour.BLACK)),
            (Position(3, 7) to pieceFactory.pawnFactory("PB3", Colour.BLACK)),
            (Position(4, 7) to pieceFactory.pawnFactory("PB4", Colour.BLACK)),
            (Position(5, 7) to pieceFactory.pawnFactory("PB5", Colour.BLACK)),
            (Position(6, 7) to pieceFactory.pawnFactory("PB6", Colour.BLACK)),
            (Position(7, 7) to pieceFactory.pawnFactory("PB7", Colour.BLACK)),
            (Position(8, 7) to pieceFactory.pawnFactory("PB8", Colour.BLACK)),

            (Position(1, 8) to pieceFactory.rookFactory("RB1", Colour.BLACK)),
            (Position(2, 8) to pieceFactory.knightFactory("KB1", Colour.BLACK)),
            (Position(3, 8) to pieceFactory.bishopFactory("BB1", Colour.BLACK)),
            (Position(4, 8) to pieceFactory.queenFactory("QB", Colour.BLACK)),
            (Position(5, 8) to pieceFactory.kingFactory("KB", Colour.BLACK)),
            (Position(6, 8) to pieceFactory.bishopFactory("BB2", Colour.BLACK)),
            (Position(7, 8) to pieceFactory.knightFactory("KB2", Colour.BLACK)),
            (Position(8, 8) to pieceFactory.rookFactory("RB2", Colour.BLACK))
        )
        return Board(pieceMap, 8, 8)
    }

    fun initialiceCapaBlancaBoard(): Board {
        val pieceFactory = PieceFactory()
        val pieceMap = mapOf(
            (Position(1, 1) to pieceFactory.rookFactory("RW1", Colour.WHITE)),
            (Position(2, 1) to pieceFactory.knightFactory("KW1", Colour.WHITE)),
            (Position(3, 1) to pieceFactory.archbishopFactory("AW", Colour.WHITE)),
            (Position(4, 1) to pieceFactory.bishopFactory("BW", Colour.WHITE)),
            (Position(5, 1) to pieceFactory.queenFactory("QW", Colour.WHITE)),
            (Position(6, 1) to pieceFactory.kingFactory("KW", Colour.WHITE)),
            (Position(7, 1) to pieceFactory.bishopFactory("BW2", Colour.WHITE)),
            (Position(8, 1) to pieceFactory.chancellorFactory("CW", Colour.WHITE)),
            (Position(9, 1) to pieceFactory.knightFactory("KW2", Colour.WHITE)),
            (Position(10, 1) to pieceFactory.rookFactory("RW2", Colour.WHITE)),


            (Position(1, 2) to pieceFactory.pawnFactory("PW1", Colour.WHITE)),
            (Position(2, 2) to pieceFactory.pawnFactory("PW2", Colour.WHITE)),
            (Position(3, 2) to pieceFactory.pawnFactory("PW3", Colour.WHITE)),
            (Position(4, 2) to pieceFactory.pawnFactory("PW4", Colour.WHITE)),
            (Position(5, 2) to pieceFactory.pawnFactory("PW5", Colour.WHITE)),
            (Position(6, 2) to pieceFactory.pawnFactory("PW6", Colour.WHITE)),
            (Position(7, 2) to pieceFactory.pawnFactory("PW7", Colour.WHITE)),
            (Position(8, 2) to pieceFactory.pawnFactory("PW8", Colour.WHITE)),
            (Position(9, 2) to pieceFactory.pawnFactory("PW9", Colour.WHITE)),
            (Position(10, 2) to pieceFactory.pawnFactory("PW10", Colour.WHITE)),


            (Position(1, 7) to pieceFactory.pawnFactory("PB1", Colour.BLACK)),
            (Position(2, 7) to pieceFactory.pawnFactory("PB2", Colour.BLACK)),
            (Position(3, 7) to pieceFactory.pawnFactory("PB3", Colour.BLACK)),
            (Position(4, 7) to pieceFactory.pawnFactory("PB4", Colour.BLACK)),
            (Position(5, 7) to pieceFactory.pawnFactory("PB5", Colour.BLACK)),
            (Position(6, 7) to pieceFactory.pawnFactory("PB6", Colour.BLACK)),
            (Position(7, 7) to pieceFactory.pawnFactory("PB7", Colour.BLACK)),
            (Position(8, 7) to pieceFactory.pawnFactory("PB8", Colour.BLACK)),
            (Position(9, 7) to pieceFactory.pawnFactory("PB9", Colour.BLACK)),
            (Position(10, 7) to pieceFactory.pawnFactory("PB10", Colour.BLACK)),


            (Position(1, 8) to pieceFactory.rookFactory("RB1", Colour.BLACK)),
            (Position(2, 8) to pieceFactory.knightFactory("KB1", Colour.BLACK)),
            (Position(3, 8) to pieceFactory.archbishopFactory("AB", Colour.BLACK)),
            (Position(4, 8) to pieceFactory.bishopFactory("BB1", Colour.BLACK)),
            (Position(5, 8) to pieceFactory.queenFactory("QB", Colour.BLACK)),
            (Position(6, 8) to pieceFactory.kingFactory("KB", Colour.BLACK)),
            (Position(7, 8) to pieceFactory.bishopFactory("BB2", Colour.BLACK)),
            (Position(8, 8) to pieceFactory.chancellorFactory("CB", Colour.BLACK)),
            (Position(9, 8) to pieceFactory.knightFactory("KB2", Colour.BLACK)),
            (Position(10, 8) to pieceFactory.rookFactory("RB2", Colour.BLACK)),

            )
        return Board(pieceMap, 8, 10)
    }

    fun initialiceCheckersBoard(): Board {
        val pieceFactory = PieceFactory()
        val pieceMap = mapOf(
            (Position(1, 1) to pieceFactory.checkerFactory("CW1", Colour.WHITE)),
            (Position(3, 1) to pieceFactory.checkerFactory("CW2", Colour.WHITE)),
            (Position(5, 1) to pieceFactory.checkerFactory("CW3", Colour.WHITE)),
            (Position(7, 1) to pieceFactory.checkerFactory("CW4", Colour.WHITE)),
            (Position(2, 2) to pieceFactory.checkerFactory("CW5", Colour.WHITE)),
            (Position(4, 2) to pieceFactory.checkerFactory("CW6", Colour.WHITE)),
            (Position(6, 2) to pieceFactory.checkerFactory("CW7", Colour.WHITE)),
            (Position(8, 2) to pieceFactory.checkerFactory("CW8", Colour.WHITE)),
            (Position(1, 3) to pieceFactory.checkerFactory("CW9", Colour.WHITE)),
            (Position(3, 3) to pieceFactory.checkerFactory("CW10", Colour.WHITE)),
            (Position(5, 3) to pieceFactory.checkerFactory("CW11", Colour.WHITE)),
            (Position(7, 3) to pieceFactory.checkerFactory("CW12", Colour.WHITE)),

            (Position(2, 6) to pieceFactory.checkerFactory("CB1", Colour.BLACK)),
            (Position(4, 6) to pieceFactory.checkerFactory("CB2", Colour.BLACK)),
            (Position(6, 6) to pieceFactory.checkerFactory("CB3", Colour.BLACK)),
            (Position(8, 6) to pieceFactory.checkerFactory("CB4", Colour.BLACK)),
            (Position(1, 7) to pieceFactory.checkerFactory("CB5", Colour.BLACK)),
            (Position(3, 7) to pieceFactory.checkerFactory("CB6", Colour.BLACK)),
            (Position(5, 7) to pieceFactory.checkerFactory("CB7", Colour.BLACK)),
            (Position(7, 7) to pieceFactory.checkerFactory("CB8", Colour.BLACK)),
            (Position(2, 8) to pieceFactory.checkerFactory("CB9", Colour.BLACK)),
            (Position(4, 8) to pieceFactory.checkerFactory("CB10", Colour.BLACK)),
            (Position(6, 8) to pieceFactory.checkerFactory("CB11", Colour.BLACK)),
            (Position(8, 8) to pieceFactory.checkerFactory("CB12", Colour.BLACK))
        )
        return Board(pieceMap, 8, 8)
    }
}