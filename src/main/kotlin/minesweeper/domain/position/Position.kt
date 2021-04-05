package minesweeper.domain.position

data class Position(
    private val x: Coordinate,
    private val y: Coordinate
) : Comparable<Position> {

    override fun compareTo(other: Position): Int {
        return if (y.compareTo(other.y) == 0) {
            x.compareTo(other.x)
        } else {
            y.compareTo(other.y)
        }
    }
}
