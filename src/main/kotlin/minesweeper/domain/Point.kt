package minesweeper.domain

data class Point(val row: Int, val col: Int) {
    operator fun plus(other: Point) = Point(row + other.row, col + other.col)

    fun getAdjacentPoints(): Set<Point> {
        return setOf(
            Point(row - 1, col - 1), Point(row - 1, col), Point(row - 1, col + 1),
            Point(row, col - 1), Point(row, col + 1),
            Point(row + 1, col - 1), Point(row + 1, col), Point(row + 1, col + 1)
        )
    }
}
