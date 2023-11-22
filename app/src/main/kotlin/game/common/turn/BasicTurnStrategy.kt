package game.common.turn

import game.common.piece.Colour
import game.common.piece.Colour.*


class BasicTurnStrategy(val colour: Colour) : TurnStrategy {
    override fun advanceTurn(): TurnStrategy {
        when(colour){
            WHITE -> return BasicTurnStrategy(BLACK)
            BLACK -> return BasicTurnStrategy(WHITE)
        }
    }

    override fun getCurrentColour(): Colour {
        return colour
    }

}