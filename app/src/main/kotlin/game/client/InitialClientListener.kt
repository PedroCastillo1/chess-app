package game.client
import edu.austral.dissis.chess.gui.InitialState
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class InitialClientListener(private val client: GameClient):MessageListener<InitialState> {
    override fun handleMessage(message: Message<InitialState>) {
        client.handleInitialState(message.payload)
    }
}