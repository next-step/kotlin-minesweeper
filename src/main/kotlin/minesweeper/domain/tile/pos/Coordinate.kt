package minesweeper.domain.tile.pos

data class Coordinate(private val positionX: Position, private val positionY: Position) {
    companion object {
        fun of(positionX: Int, positionY: Int) = Coordinate(Position(positionX), Position(positionY))
    }
}
