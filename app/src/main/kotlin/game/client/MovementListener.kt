package game.client
import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move

class MovementListener(private val client: GameClient): GameEventListener {
    override fun handleMove(move: Move) {
        client.move(move)
    }
}