package minesweeper.domain.point

data class Point(val x: Int, val y: Int) : Comparable<Point> {
    init {
        require(x >= 0) { "x좌표 값은 0보다 커야 합니다." }
        require(y >= 0) { "y좌표 값은 0보다 커야 합니다." }
    }

    override fun compareTo(other: Point): Int = if (y == other.y) x.compareTo(other.x) else y.compareTo(other.y)

    fun adjacent(): List<Point> = Direction.values()
        .map { nextPoint(it) }
        .filter { it != this }

    private fun nextPoint(direction: Direction): Point = when (direction) {
        Direction.UP -> Point(x, (y - 1).coerceZero())
        Direction.UP_RIGHT -> Point(x + 1, (y - 1).coerceZero())
        Direction.RIGHT -> Point(x + 1, y)
        Direction.DOWN_RIGHT -> Point(x + 1, y + 1)
        Direction.DOWN -> Point(x, y + 1)
        Direction.DOWN_LEFT -> Point((x - 1).coerceZero(), y + 1)
        Direction.LEFT -> Point((x - 1).coerceZero(), y)
        Direction.UP_LEFT -> Point((x - 1).coerceZero(), (y - 1).coerceZero())
    }

    private fun Int.coerceZero(): Int = this.coerceAtLeast(0)
    companion object {
        fun square(height: Int, width: Int): List<Point> =
            (0 until height).flatMap { y -> (0 until width).map { x -> Point(x, y) } }
    }
}
