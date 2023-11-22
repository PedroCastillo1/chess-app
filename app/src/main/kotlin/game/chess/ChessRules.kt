package game.chess

import adt.*
import game.common.factory.GameStateFactory
import game.common.history.HistoryUpdater
import game.chess.state.ChessStateEvaluator
import game.common.movement.Movement
import game.common.mover.PieceMover
import edu.austral.dissis.chess.gui.GameOver
import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.dissis.chess.gui.MoveResult
import game.common.piece.Colour
import game.chess.promotion.NormalPromotion
import game.common.Adapter
import game.common.GameState
import game.common.Rules
import game.common.promotion.PromotionStrategy


class ChessRules : Rules {

    private val gameValidator : ChessValidator = ChessValidator()
    private val gameStateFactory : GameStateFactory = GameStateFactory()
    private val historyUpdater : HistoryUpdater = HistoryUpdater()
    private val pieceMover : PieceMover = PieceMover()
    private val chessStateEvaluator : ChessStateEvaluator = ChessStateEvaluator()
    private val promoter : PromotionStrategy = NormalPromotion()
    private val adapter = Adapter()
    private var gameState : GameState = init()

    override fun init() : GameState {
        return gameStateFactory.normalGameStateBuilder()
    }

    override fun makeAMove(move: Movement): MoveResult {
        if(isMovementSuccessful(move)){
            val afterMoveGs = historyUpdater.update(pieceMover.movePiece(move, gameState))
            val newGameState = promoter.promote(afterMoveGs)
            when(stateEvaluatorResult(newGameState)){
                is InProgressStateResult -> {
                    gameState = newGameState.copy(currColour = newGameState.currColour.advanceTurn())
                    return adapter.adaptGameState(gameState)
                }
                is TieStateResult -> return GameOver(adapter.adaptPieceColorToPlayerColor(Colour.WHITE))//EN VERDAD VER COMO PASARLE EL TIE
                is WinStateResult -> return GameOver(adapter.adaptPieceColorToPlayerColor((stateEvaluatorResult(newGameState) as WinStateResult).wonColour))
            }
        }
        return InvalidMove(invalidMovementDescription(move))
    }

    private fun isMovementSuccessful(move: Movement): Boolean{
        return gameValidator.validate(move, gameState) is ValidMovementResult
    }

    private fun stateEvaluatorResult(gs: GameState): StateResult{
        return chessStateEvaluator.validate(gs)
    }

    private fun invalidMovementDescription(move: Movement): String{
        return (gameValidator.validate(move, gameState) as InvalidMovementResult).reason
    }

    override fun getAdapter(): Adapter {
        return adapter
    }

}