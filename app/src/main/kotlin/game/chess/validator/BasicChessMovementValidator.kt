package game.chess.validator

import adt.ResultMovement
import game.common.GameState
import game.common.movement.Movement
import game.common.validator.basic.ColourMovementValidator
import game.common.validator.basic.EmptyOrEnemyMovementValidator
import game.common.validator.logic.AndMovementValidator
import game.common.validator.MovementValidator
import game.common.validator.basic.NotSamePositionMovementValidator
import game.common.validator.basic.InBoardValidator
import game.common.validator.basic.NotAPieceMovementValidator


class BasicChessMovementValidator : MovementValidator {
    override fun validate(movement: Movement, gameState: GameState): ResultMovement {
        val basicMv = AndMovementValidator(listOf(
            NotAPieceMovementValidator(),
                EmptyOrEnemyMovementValidator(), InBoardValidator(),
                NotSamePositionMovementValidator(), ColourMovementValidator(), NotInCheckMovementValidator()
        ))

        return basicMv.validate(movement, gameState)
    }
}