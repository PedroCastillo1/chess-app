package game.common.board

import game.common.piece.Piece

data class Board(val pieceMap: Map<Position, Piece>, val numRow: Int, val numCol: Int) {
    fun getPositionByPiece(piece: Piece): Position {
        return pieceMap.entries.find { it.value == piece }!!.key
    }

}
