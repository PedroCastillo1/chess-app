package game.common.history

import game.common.board.Position
import game.common.board.Board


data class History(val boardHistory: List<Board>) {

    fun getPosById(id: String): List<Position> {
        val positions = mutableListOf<Position>()

        for (board in boardHistory) {
            for ((position, piece) in board.pieceMap) {
                if (piece.id == id) {
                    positions.add(position)
                }
            }
        }
        return positions.toList()
    }
}