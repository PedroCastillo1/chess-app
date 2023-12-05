package client
import edu.austral.dissis.chess.gui.GameOver
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class GameOverListener(private val client: GameClient):MessageListener<GameOver> {
    override fun handleMessage(message: Message<GameOver>) {
        client.handleMoveResult(message.payload)
    }

}