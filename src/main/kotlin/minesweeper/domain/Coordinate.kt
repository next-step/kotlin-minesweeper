package minesweeper.domain

data class Coordinate(val row: Int, val col: Int) {

    operator fun plus(other: Coordinate): Coordinate {
        return Coordinate(this.row + other.row, this.col + other.col)
    }
    fun isOutOfBound(height: Int, width: Int): Boolean {
        return row < 0 || row >= height || col < 0 || col >= width
    }
}
