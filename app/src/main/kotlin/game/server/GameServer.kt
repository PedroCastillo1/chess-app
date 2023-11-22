package game.server

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import edu.austral.dissis.chess.gui.GameOver
import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.chess.gui.NewGameState
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder
import game.common.MyEngine


class GameServer{
    private val server:Server = NettyServerBuilder.createDefault().withPort(8080)
        .addMessageListener("move", jacksonTypeRef(), MovementListener(this))
        .addMessageListener("init", jacksonTypeRef(), InitialServerListener(this))
        .build()

    private val gameEngine = MyEngine.chessEngine()
    fun handleMove(message: Message<Move>) {
        when(val moveResult = gameEngine.applyMove(message.payload)){
            is GameOver -> server.broadcast(Message("game-over", moveResult))
            is InvalidMove -> server.broadcast(Message("invalid", moveResult))
            is NewGameState -> server.broadcast(Message("new-game-state", moveResult))
        }
    }

    fun handleInit() {
        server.broadcast(Message("init", gameEngine.init()))
    }


    fun startServer(){
        server.start()
    }

    fun stopServer(){
        server.stop()
    }
}