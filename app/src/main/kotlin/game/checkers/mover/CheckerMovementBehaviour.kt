package game.checkers.mover

import adt.ValidMovementResult
import game.common.movement.Movement
import game.common.board.Position
import game.common.piece.Piece
import game.checkers.validator.BasicCheckersValidator
import game.checkers.validator.InBetweenEnemyValidator
import game.checkers.validator.NotObligatedToEatValidator
import game.common.movement.NormalMovementBehaviour
import game.common.validator.basic.LimitMovementValidator
import game.common.GameState
import game.common.movement.MovementBehaviour
import game.common.validator.basic.movement.FowardDiagonalMovementValidator
import game.common.validator.basic.ToPositionClearValidator
import game.common.validator.logic.AndMovementValidator

class CheckerMovementBehaviour : MovementBehaviour {

    private val normalDiagonalMv = AndMovementValidator(
        listOf(
            LimitMovementValidator(1),
            ToPositionClearValidator(),
            FowardDiagonalMovementValidator(),
            NotObligatedToEatValidator()
        )
    )

    private val basicCheckersValidator = BasicCheckersValidator()

    private val eatDiagonalMv = AndMovementValidator(
        listOf(
            LimitMovementValidator(2),
            InBetweenEnemyValidator(),
            ToPositionClearValidator(),
            FowardDiagonalMovementValidator()
        )
    )

    override fun move(gameState: GameState, movement: Movement): GameState {
        if (isAnNormalMovement(movement, gameState)) {
            return NormalMovementBehaviour().move(gameState, movement)
        }
        return applyEatMovement(movement, gameState)
    }

    private fun isAnNormalMovement(movement: Movement, gameState: GameState): Boolean {
        return (normalDiagonalMv.validate(movement, gameState) is ValidMovementResult)
    }

    private fun isAnEatMovement(movement: Movement, gameState: GameState): Boolean {
        return (eatDiagonalMv.validate(movement, gameState) is ValidMovementResult)
    }

    private fun applyEatMovement(movement: Movement, gameState: GameState): GameState {
        var newGameState = NormalMovementBehaviour().move(gameState, movement)
        val newPossibleMovement = canPieceStillEat(newGameState, newGameState.getPiece(movement.to))
        if(basicCheckersValidator.validate(newPossibleMovement, newGameState) is ValidMovementResult){
            newGameState = applyEatMovement(newPossibleMovement, newGameState)
        }
        val auxX = (movement.to.column - movement.from.column)
        val auxY = (movement.to.row - movement.from.row)

        val stepX = if (auxX > 0) 1 else -1
        val stepY = if (auxY > 0) 1 else -1

        val intermediatePosition = Position(
            movement.from.column + 1 * stepX,
            movement.from.row + 1 * stepY
        )

        var newPieceMap = newGameState.getPieceMap().toMutableMap()
        newPieceMap.remove(intermediatePosition)
        val inmutableGameState = newGameState.copy(board = newGameState.board.copy(pieceMap = newPieceMap.toMap()))

        return inmutableGameState
    }

    private fun canPieceStillEat(gameState: GameState, actualPiece : Piece): Movement {
        val piecePosition = gameState.getPositionByPieceID(actualPiece.id)!!
        for (i in 1..gameState.board.numCol) {
            for (j in 1..gameState.board.numRow) {
                val toPosition = Position(i, j)
                val movement = Movement(toPosition, piecePosition)
                if(actualPiece.mv.validate(movement, gameState) is ValidMovementResult){
                    if (isAnEatMovement(movement, gameState)) {
                        return movement
                    }
                }
            }
        }
        val outOfBoundPosition = Position(-1, -1)
        return Movement(outOfBoundPosition, outOfBoundPosition)
    }

}