package minesweeper.model.point

data class Coordinate(
    private val vertical: Vertical,
    private val horizontal: Horizontal,
) {
    fun moveTo(delta: Delta): Coordinate {
        return Coordinate(
            vertical = vertical.moveTo(delta.verticalDelta),
            horizontal = horizontal.moveTo(delta.horizontalDelta)
        )
    }

    fun movePossible(delta: Delta, verticalLimit: Int, horizontalLimit: Int): Boolean {
        return vertical.movePossible(delta.verticalDelta, verticalLimit) && horizontal.movePossible(delta.horizontalDelta, horizontalLimit)
    }

    companion object {
        fun of(vertical: Int, horizontal: Int): Coordinate {
            return Coordinate(Vertical(vertical), Horizontal(horizontal))
        }
    }
}
