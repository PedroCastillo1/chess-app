package game.checkers.rules.mover

import adt.*
import edu.austral.dissis.chess.gui.GameOver
import edu.austral.dissis.chess.gui.MoveResult
import game.checkers.CheckersValidator
import game.checkers.promotion.CheckersPromotion
import game.checkers.state.CheckersStateEvaluator
import adapter.Adapter
import game.common.GameState
import game.common.history.HistoryUpdater
import game.common.movement.Movement
import game.common.mover.PieceMover
import game.common.piece.Colour
import game.common.rules.RulesChecker

class CheckersRulesChecker : RulesChecker {
    private val gameValidator : CheckersValidator = CheckersValidator()
    private val checkersStateEvaluator : CheckersStateEvaluator = CheckersStateEvaluator()
    override fun makeAMove(move: Movement, gameState: GameState) : StateResult {
            return when(stateEvaluatorResult(gameState)){
                is InProgressStateResult -> {
                    InProgressStateResult()
                }

                is TieStateResult -> TieStateResult("Tie")
                is WinStateResult -> WinStateResult((stateEvaluatorResult(gameState) as WinStateResult).wonColour)
            }
    }
    override fun isMovementSuccessful(move: Movement, gameState: GameState): Boolean{
        return gameValidator.validate(move, gameState) is ValidMovementResult
    }
    private fun stateEvaluatorResult(gs: GameState): StateResult{
        return checkersStateEvaluator.validate(gs)
    }

}
