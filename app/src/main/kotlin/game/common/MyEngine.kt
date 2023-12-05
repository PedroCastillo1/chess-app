package game.common

import adapter.Adapter
import adt.InProgressStateResult
import adt.TieStateResult
import adt.WinStateResult
import edu.austral.dissis.chess.gui.*
import game.checkers.promotion.CheckersPromotion
import game.checkers.rules.mover.CheckersRulesChecker
import game.chess.rules.mover.ChessRulesChecker
import game.common.rules.RulesImpl
import game.chess.rules.initializer.CapaBlancaInitializer
import game.checkers.rules.initializer.CheckersInitializer
import game.chess.promotion.NormalPromotion
import game.chess.rules.initializer.ClassicChessInitializer
import game.common.movement.Movement
import game.common.piece.Colour
import game.common.rules.Rules


class MyEngine(rules: Rules) : GameEngine {
    val game = rules
    private val adapter = Adapter()

    companion object{
        fun chessEngine() : MyEngine {
            return MyEngine(RulesImpl(ClassicChessInitializer(), ChessRulesChecker(), NormalPromotion()))
        }

        fun checkersEngine() : MyEngine {
            return MyEngine(RulesImpl(CheckersInitializer(), CheckersRulesChecker(), CheckersPromotion()))
        }

        fun capaBlancaEngine() : MyEngine {
            return MyEngine(RulesImpl(CapaBlancaInitializer(), ChessRulesChecker(), NormalPromotion()))
        }
    }

    override fun applyMove(move: Move): MoveResult {
        val movement : Movement = adapter.translateMoveToMovement(move)
        return when(val stateResult = game.makeAMove(movement)){
            is InProgressStateResult -> {
                adapter.adaptGameState(game.getGameState())
            }
            is TieStateResult -> GameOver(adapter.adaptPieceColorToPlayerColor(Colour.WHITE))
            is WinStateResult -> GameOver(adapter.adaptPieceColorToPlayerColor(stateResult.wonColour))
        }
    }

    override fun init(): InitialState {
        return adapter.adaptGameStateToInitialState(game.init())
    }
}