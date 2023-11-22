package game.common.piece
import game.common.movement.MovementBehaviour
import game.common.validator.MovementValidator

data class Piece(val id: String, val type: String, val mv: MovementValidator, val colour: Colour, val mb: MovementBehaviour)
