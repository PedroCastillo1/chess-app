package client
import edu.austral.dissis.chess.gui.NewGameState
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class SuccesfullMovementListener(private val client: GameClient): MessageListener<NewGameState> {
    override fun handleMessage(message: Message<NewGameState>) {
        client.handleMoveResult(message.payload)
    }
}