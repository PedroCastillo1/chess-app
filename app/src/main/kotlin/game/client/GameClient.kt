package game.client

import edu.austral.dissis.chess.gui.GameView
import edu.austral.dissis.chess.gui.InitialState
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.chess.gui.MoveResult
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import javafx.application.Platform
import java.net.InetSocketAddress
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef



class GameClient(private val gameView: GameView) {
    private val client =
        NettyClientBuilder.createDefault()
            .withAddress(InetSocketAddress(8080))
            .addMessageListener("invalid", jacksonTypeRef(), InvalidMovementListener(this))
            .addMessageListener("game-over", jacksonTypeRef(), GameOverListener(this))
            .addMessageListener("new-game-state", jacksonTypeRef(), SuccesfullMovementListener(this))
            .addMessageListener("init", jacksonTypeRef(), InitialClientListener(this))
            .build();


    init {
        client.connect()
        Thread.sleep(200)
        client.send(Message("init", Unit))
    }
    fun handleMoveResult(moveResult: MoveResult){
        Platform.runLater {
            gameView.handleMoveResult(moveResult)
        }
    }

    fun move(move: Move){
        client.send(Message("move", move))
    }

    fun handleInitialState(payload: InitialState) {
        Platform.runLater {
            gameView.handleInitialState(payload);
        }

    }
}