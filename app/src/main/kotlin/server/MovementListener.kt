package server

import edu.austral.dissis.chess.gui.Move
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener


class MovementListener(private val server: GameServer): MessageListener<Move> {
    override fun handleMessage(message: Message<Move>) {
        server.handleMove(message)
    }


}