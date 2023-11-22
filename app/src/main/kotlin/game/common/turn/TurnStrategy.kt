package game.common.turn

import game.common.piece.Colour

interface TurnStrategy {

    fun advanceTurn() : TurnStrategy
    fun getCurrentColour(): Colour

}