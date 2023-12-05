package game.common.rules

import edu.austral.dissis.chess.gui.MoveResult
import adapter.Adapter
import adt.InProgressStateResult
import adt.StateResult
import edu.austral.dissis.chess.gui.InvalidMove
import game.common.GameState
import game.common.history.HistoryUpdater
import game.common.initializer.GameInitializer
import game.common.movement.Movement
import game.common.mover.PieceMover
import game.common.promotion.PromotionStrategy

class RulesImpl(private val gameInitializer: GameInitializer, private val rulesChecker: RulesChecker, private val promotionStrategy: PromotionStrategy) : Rules {
    private var gameState: GameState = init()
    private val historyUpdater : HistoryUpdater = HistoryUpdater()
    private val pieceMover : PieceMover = PieceMover()
    override fun init(): GameState {
        return gameInitializer.init()
    }
    override fun makeAMove(move: Movement): StateResult {
        if (rulesChecker.isMovementSuccessful(move, gameState)) {
            val afterMoveGs = historyUpdater.update(pieceMover.movePiece(move, gameState))
            val newGameState = promotionStrategy.promote(afterMoveGs)
            val moveResult = rulesChecker.makeAMove(move, newGameState)
            gameState = newGameState.copy(currColour = newGameState.currColour.advanceTurn())
            return moveResult
        }
        return InProgressStateResult()
    }

    override fun getGameState(): GameState {
        return gameState
    }

}