package minesweeper.domain

data class Point(val row: Int, val col: Int) {
    operator fun plus(other: Point) = Point(row + other.row, col + other.col)

    fun isWithin(height: Int, width: Int) = row in 0 until height && col in 0 until width
}
