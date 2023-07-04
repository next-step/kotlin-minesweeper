package minesweeper.domain

data class Point(val x: Int, val y: Int) : Comparable<Point> {
    init {
        require(x >= 0) { "x좌표 값은 0보다 커야 합니다." }
        require(y >= 0) { "y좌표 값은 0보다 커야 합니다." }
    }

    override fun compareTo(other: Point): Int = if (y == other.y) x.compareTo(other.x) else y.compareTo(other.y)

    fun adjacent(): List<Point> = Direction.values().mapNotNull { nextPoint(it).getOrNull() }
    fun nextPoint(direction: Direction): Result<Point> = runCatching {
        when (direction) {
            Direction.UP -> Point(x, y - 1)
            Direction.UP_RIGHT -> Point(x + 1, y - 1)
            Direction.RIGHT -> Point(x + 1, y)
            Direction.DOWN_RIGHT -> Point(x + 1, y + 1)
            Direction.DOWN -> Point(x, y + 1)
            Direction.DOWN_LEFT -> Point(x - 1, y + 1)
            Direction.LEFT -> Point(x - 1, y)
            Direction.UP_LEFT -> Point(x - 1, y - 1)
        }
    }

    companion object {
        fun square(height: Int, width: Int): List<Point> =
            (0 until height).flatMap { y -> (0 until width).map { x -> Point(x, y) } }
    }
}

enum class Direction {
    UP,
    UP_RIGHT,
    RIGHT,
    DOWN_RIGHT,
    DOWN,
    DOWN_LEFT,
    LEFT,
    UP_LEFT,
}
