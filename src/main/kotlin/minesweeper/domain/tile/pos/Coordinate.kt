package minesweeper.domain.tile.pos

data class Coordinate(private val positionX: Position, private val positionY: Position) {
    fun getPositionX() = positionX.value

    fun getPositionY() = positionY.value

    companion object {
        fun of(positionX: Int, positionY: Int) = Coordinate(Position(positionX), Position(positionY))
    }
}
