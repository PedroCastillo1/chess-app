package game.common

import edu.austral.dissis.chess.gui.*
import game.checkers.CheckersRules
import game.chess.CapaBlancaRules
import game.chess.ChessRules
import game.common.movement.Movement


class MyEngine(rules: Rules) : GameEngine {
    val game = rules

    companion object{
        fun chessEngine() : MyEngine {
            return MyEngine(ChessRules())
        }

        fun checkersEngine() : MyEngine {
            return MyEngine(CheckersRules())
        }

        fun capaBlancaEngine() : MyEngine {
            return MyEngine(CapaBlancaRules())
        }
    }

    override fun applyMove(move: Move): MoveResult {
        val movement : Movement = game.getAdapter().translateMoveToMovement(move)
        return game.makeAMove(movement)
    }

    override fun init(): InitialState {
        return game.getAdapter().adaptGameStateToInitialState(game.init())
    }
}