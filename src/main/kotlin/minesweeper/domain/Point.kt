package minesweeper.domain

data class Point(val row: Int, val col: Int) {
    operator fun plus(other: Point) = Point(row + other.row, col + other.col)

    fun isWithin(height: Int, width: Int): Boolean {
        return row in 0 until height && col in 0 until width
    }
    fun getAdjacentPoints(): Set<Point> {
        return setOf(
            Point(row - 1, col - 1), Point(row - 1, col), Point(row - 1, col + 1),
            Point(row, col - 1), Point(row, col + 1),
            Point(row + 1, col - 1), Point(row + 1, col), Point(row + 1, col + 1)
        )
    }
}
