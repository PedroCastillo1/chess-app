package game.common.factory

import game.common.movement.NormalMovementBehaviour
import game.common.piece.Colour
import game.checkers.mover.CheckerMovementBehaviour
import game.checkers.mover.CrownedMovementBehaviour
import game.checkers.validator.InBetweenEnemyValidator
import game.checkers.validator.NotObligatedToEatValidator
import game.common.validator.logic.AndMovementValidator
import game.chess.mover.KingMovementBehaviour
import game.chess.validator.*
import game.common.piece.Piece
import game.common.validator.basic.*
import game.common.validator.basic.movement.*
import game.common.validator.logic.OrMovementValidator


class PieceFactory {

    fun rookFactory(id: String, colour: Colour): Piece {
        val horizontalMv = AndMovementValidator(listOf(HorizontalMovementValidator(), PathClearValidator()))
        val verticalMv = AndMovementValidator(listOf(VerticalMovementValidator(), PathClearValidator()))
        val mv = OrMovementValidator(listOf(verticalMv, horizontalMv))
        return Piece(id, "ROOK", mv, colour, NormalMovementBehaviour())
    }

    fun pawnFactory(id: String, colour: Colour): Piece {
        val normalMv = AndMovementValidator(
            listOf(
                FowardMovementValidator(),
                LimitMovementValidator(1),
                ToPositionClearValidator()
            )
        )
        val firstMoveMv = AndMovementValidator(
            listOf(
                FowardMovementValidator(),
                LimitMovementValidator(2),
                MaxMovementCount(1, id),
                PathClearValidator(),
                ToPositionClearValidator()
            )
        )
        val eatFowardMv = AndMovementValidator(
            listOf(
                FowardDiagonalMovementValidator(),
                OtherColourMovementValidator(),
                LimitMovementValidator(1)
            )
        )
        val mv = OrMovementValidator(listOf(normalMv, firstMoveMv, eatFowardMv))
        return Piece(id, "PAWN", mv, colour, NormalMovementBehaviour())
    }

    fun kingFactory(id: String, colour: Colour): Piece {
        val horizontalMv =
            AndMovementValidator(listOf(HorizontalMovementValidator(), PathClearValidator(), LimitMovementValidator(1)))
        val verticalMv =
            AndMovementValidator(listOf(VerticalMovementValidator(), PathClearValidator(), LimitMovementValidator(1)))
        val diagonalMv = AndMovementValidator(
            listOf(
                DiagonalMovementValidator(), PathClearValidator(),
                LimitMovementValidator(1)
            )
        )
        val mv = OrMovementValidator(listOf(verticalMv, horizontalMv, diagonalMv, CastlingMV()))
        return Piece(id, "KING", mv, colour, KingMovementBehaviour())
    }

    fun bishopFactory(id: String, colour: Colour): Piece {
        val mv = AndMovementValidator(listOf(DiagonalMovementValidator(), PathClearValidator()))
        return Piece(id, "BISHOP", mv, colour, NormalMovementBehaviour())
    }

    fun queenFactory(id: String, colour: Colour): Piece {
        val horizontalMv = AndMovementValidator(listOf(HorizontalMovementValidator(), PathClearValidator()))
        val verticalMv = AndMovementValidator(listOf(VerticalMovementValidator(), PathClearValidator()))
        val diagonalMv = AndMovementValidator(listOf(DiagonalMovementValidator(), PathClearValidator()))

        val mv = OrMovementValidator(listOf(verticalMv, horizontalMv, diagonalMv))
        return Piece(id, "QUEEN", mv, colour, NormalMovementBehaviour())
    }

    fun knightFactory(id: String, colour: Colour): Piece {
        return Piece(id, "KNIGHT", JumpMovementValidator(1, 2), colour, NormalMovementBehaviour())
    }

    //EXTRA PIECES:
    fun archbishopFactory(id: String, colour: Colour): Piece {
        val mv = OrMovementValidator(
            listOf(
                AndMovementValidator(
                    listOf(DiagonalMovementValidator(), PathClearValidator())
                )
                , JumpMovementValidator(1, 2)
            )
        )
        return Piece(id, "ARCHBISHOP", mv, colour, NormalMovementBehaviour())
    }

    fun chancellorFactory(id: String, colour: Colour): Piece {
        val horizontalMv = AndMovementValidator(listOf(HorizontalMovementValidator(), PathClearValidator()))
        val verticalMv = AndMovementValidator(listOf(VerticalMovementValidator(), PathClearValidator()))
        val mv = OrMovementValidator(
            listOf(verticalMv, horizontalMv, JumpMovementValidator(1, 2))
        )
        return Piece(id, "CHANCELLOR", mv, colour, NormalMovementBehaviour())
    }


    fun checkerFactory(id: String, colour: Colour): Piece {
        val normalDiagonalMv = AndMovementValidator(
            listOf(
                LimitMovementValidator(1),
                ToPositionClearValidator(),
                FowardDiagonalMovementValidator(),
                NotObligatedToEatValidator()
            )
        )

        val eatDiagonalMv = AndMovementValidator(
            listOf(
                LimitMovementValidator(2),
                InBetweenEnemyValidator(),
                ToPositionClearValidator(),
                FowardDiagonalMovementValidator()
            )
        )

        val mv = OrMovementValidator(listOf(eatDiagonalMv, normalDiagonalMv))
        return Piece(id, "PAWN", mv, colour, CheckerMovementBehaviour())
    }

    fun crownedFactory(id: String, colour: Colour): Piece {
        val normalDiagonalMv = AndMovementValidator(
            listOf(
                DiagonalMovementValidator(),
                LimitMovementValidator(1),
                ToPositionClearValidator(),
                NotObligatedToEatValidator()
            )
        )

        val eatDiagonalMv = AndMovementValidator(
            listOf(
                DiagonalMovementValidator(),
                LimitMovementValidator(2),
                InBetweenEnemyValidator(),
                ToPositionClearValidator(),
            )
        )

        val mv = OrMovementValidator(listOf(eatDiagonalMv, normalDiagonalMv))
        return Piece(id, "KING", mv, colour, CrownedMovementBehaviour())
    }

}