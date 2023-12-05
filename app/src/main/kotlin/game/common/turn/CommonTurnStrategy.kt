package game.common.turn

import game.common.piece.Colour
import game.common.piece.Colour.*


class CommonTurnStrategy(val colour: Colour) : TurnStrategy {
    override fun advanceTurn(): TurnStrategy {
        return when(colour){
            WHITE -> CommonTurnStrategy(BLACK)
            BLACK -> CommonTurnStrategy(WHITE)
        }
    }

    override fun getCurrentColour(): Colour {
        return colour
    }

}