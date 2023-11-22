package game.checkers

import adt.*
import game.common.factory.GameStateFactory
import game.common.history.HistoryUpdater
import game.common.movement.Movement
import game.common.mover.PieceMover
import edu.austral.dissis.chess.gui.GameOver
import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.dissis.chess.gui.MoveResult
import game.checkers.promotion.CheckersPromotion
import game.checkers.state.CheckersStateEvaluator
import game.common.Adapter
import game.common.GameState
import game.common.Rules
import game.common.piece.Colour

class CheckersRules : Rules {

    private val gameValidator : CheckersValidator = CheckersValidator()
    private val gameStateFactory : GameStateFactory = GameStateFactory()
    private val historyUpdater : HistoryUpdater = HistoryUpdater()
    private val pieceMover : PieceMover = PieceMover()
    private val checkersStateEvaluator : CheckersStateEvaluator = CheckersStateEvaluator()
    private val promoter : CheckersPromotion = CheckersPromotion()
    private val adapter = Adapter()
    private var gameState : GameState = init()

    override fun init() : GameState {
        return gameStateFactory.checkersStateBuilder()
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
        return checkersStateEvaluator.validate(gs)
    }

    private fun invalidMovementDescription(move: Movement): String{
        return (gameValidator.validate(move, gameState) as InvalidMovementResult).reason
    }

    override fun getAdapter(): Adapter {
        return adapter
    }

}