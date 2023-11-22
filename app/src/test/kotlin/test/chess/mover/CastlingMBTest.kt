package test.chess.mover


class CastlingMBTest {
//    val gameStateFactory = GameStateFactory()
//
//    @Test
//    fun `test long castling movement behaviour`() {
//        val initialGs = canLongCastleGameState()
//        val movement = Movement(Position(3, 1), Position(5, 1))
//        val finalGs = KingMovementBehaviour().move(initialGs, movement)
//
//        assertEquals(longCastleMadeGameState(), finalGs)
//    }
//
//    @Test
//    fun `test short castling movement behaviour`() {
//        val initialGs = canShortCastleGameState()
//        val pieceToMove = initialGs.getPiece(Position(5, 1))!!
//        val movement = Movement(Position(7, 1), Position(5, 1))
//        val finalGs = shortCastleMadeGameState()
//
//        assertEquals(shortCastleMadeGameState(), finalGs)
//    }
//
//    private fun canLongCastleGameState(): GameState {
//        val pieceFactory = PieceFactory()
//        val pieceMap = mapOf(
//            (Position(1, 1) to pieceFactory.rookFactory("RW1", Colour.WHITE)),
//            (Position(5, 1) to pieceFactory.kingFactory("KW", Colour.WHITE)),
//            (Position(6, 1) to pieceFactory.bishopFactory("BW2", Colour.WHITE)),
//            (Position(7, 1) to pieceFactory.knightFactory("KW2", Colour.WHITE)),
//            (Position(8, 1) to pieceFactory.rookFactory("RW2", Colour.WHITE)),
//
//            (Position(5, 8) to pieceFactory.kingFactory("KB", Colour.BLACK)),
//        )
//        val longCastlingBoard = Board(pieceMap, 8, 8)
//        return gameStateFactory.normalGameStateBuilder().copy(board = longCastlingBoard)
//    }
//
//    private fun canShortCastleGameState(): GameState {
//        val pieceFactory = PieceFactory()
//        val pieceMap = mapOf(
//            (Position(5, 1) to pieceFactory.kingFactory("KW", Colour.WHITE)),
//            (Position(8, 1) to pieceFactory.rookFactory("RW2", Colour.WHITE)),
//            (Position(1, 1) to pieceFactory.rookFactory("RW1", Colour.WHITE)),
//            (Position(2, 1) to pieceFactory.knightFactory("KW1", Colour.WHITE)),
//            (Position(3, 1) to pieceFactory.bishopFactory("BW1", Colour.WHITE)),
//            (Position(4, 1) to pieceFactory.queenFactory("QW", Colour.WHITE)),
//
//            (Position(5, 8) to pieceFactory.kingFactory("KB", Colour.BLACK)),
//        )
//        val longCastlingBoard = Board(pieceMap, 8, 8)
//        return gameStateFactory.normalGameStateBuilder().copy(board = longCastlingBoard)
//    }
//
//    private fun longCastleMadeGameState(): GameState {
//        val pieceFactory = PieceFactory()
//        val pieceMap = mapOf(
//            (Position(3, 1) to pieceFactory.kingFactory("KW", Colour.WHITE)),
//            (Position(4, 1) to pieceFactory.rookFactory("RW1", Colour.WHITE)),
//            (Position(6, 1) to pieceFactory.bishopFactory("BW2", Colour.WHITE)),
//            (Position(7, 1) to pieceFactory.knightFactory("KW2", Colour.WHITE)),
//            (Position(8, 1) to pieceFactory.rookFactory("RW2", Colour.WHITE)),
//
//            (Position(5, 8) to pieceFactory.kingFactory("KB", Colour.BLACK)),
//        )
//        val longCastlingBoard = Board(pieceMap, 8, 8)
//        return gameStateFactory.normalGameStateBuilder().copy(board = longCastlingBoard)
//    }
//    private fun shortCastleMadeGameState(): GameState {
//        val pieceFactory = PieceFactory()
//        val pieceMap = mapOf(
//            (Position(1, 1) to pieceFactory.rookFactory("RW1", Colour.WHITE)),
//            (Position(2, 1) to pieceFactory.knightFactory("KW1", Colour.WHITE)),
//            (Position(3, 1) to pieceFactory.bishopFactory("BW1", Colour.WHITE)),
//            (Position(4, 1) to pieceFactory.queenFactory("QW", Colour.WHITE)),
//            (Position(7, 1) to pieceFactory.kingFactory("KW", Colour.WHITE)),
//            (Position(6, 1) to pieceFactory.rookFactory("RW2", Colour.WHITE)),
//
//            (Position(5, 8) to pieceFactory.kingFactory("KB", Colour.BLACK)),
//
//        )
//        val longCastlingBoard = Board(pieceMap, 8, 8)
//        return gameStateFactory.normalGameStateBuilder().copy(board = longCastlingBoard)
//    }


}