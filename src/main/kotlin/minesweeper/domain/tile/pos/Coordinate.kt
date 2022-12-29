package minesweeper.domain.tile.pos

data class Coordinate(private val posX: Position, private val posY: Position) {
    companion object {
        fun of(posX: Int, posY: Int) = Coordinate(Position(posX), Position(posY))
    }
}
