package server

import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener


class InitialServerListener(private val server: GameServer): MessageListener<Unit> {
    override fun handleMessage(message: Message<Unit>) {
        server.handleInit()
    }

}