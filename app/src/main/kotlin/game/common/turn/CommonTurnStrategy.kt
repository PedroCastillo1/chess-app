package game.common.turn

import game.common.piece.Colour
import game.common.piece.Colour.*


class CommonTurnStrategy(val colour: Colour) : TurnStrategy {
    override fun advanceTurn(): TurnStrategy {
        when(colour){
            WHITE -> return CommonTurnStrategy(BLACK)
            BLACK -> return CommonTurnStrategy(WHITE)
        }
    }

    override fun getCurrentColour(): Colour {
        return colour
    }

}