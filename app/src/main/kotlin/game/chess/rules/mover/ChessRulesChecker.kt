package game.chess.rules.mover

import adt.*
import edu.austral.dissis.chess.gui.GameOver
import edu.austral.dissis.chess.gui.MoveResult
import game.chess.ChessValidator
import game.chess.state.ChessStateEvaluator
import adapter.Adapter
import game.common.GameState
import game.common.movement.Movement
import game.common.piece.Colour
import game.common.rules.RulesChecker

class ChessRulesChecker : RulesChecker {
    private val gameValidator : ChessValidator = ChessValidator()
    private val chessStateEvaluator : ChessStateEvaluator = ChessStateEvaluator()
    override fun makeAMove(move: Movement, gameState: GameState): StateResult {
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
    private fun stateEvaluatorResult(gs: GameState): StateResult {
        return chessStateEvaluator.validate(gs)
    }

}