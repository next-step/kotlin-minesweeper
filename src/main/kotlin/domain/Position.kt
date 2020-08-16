package domain

data class Position(
    val x: Int,
    val y: Int
) : Comparable<Position> {

    override fun compareTo(other: Position): Int {
        if (this.y > other.y) return 1
        if (this.y == other.y && this.x > other.x) return 1
        return -1
    }

    companion object {
        private const val POSITION_START = 1

        fun createAll(rectangle: Rectangle): List<Position> {
            return (POSITION_START..rectangle.height).flatMap { y ->
                (POSITION_START..rectangle.width).map { Position(it, y) }
            }
        }
    }
}
