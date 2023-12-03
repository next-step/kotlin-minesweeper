package minesweeper.domain

data class Coordinate(val row: Int, val col: Int) {

    operator fun plus(other: Coordinate): Coordinate {
        return Coordinate(this.row + other.row, this.col + other.col)
    }

    fun isOutOfBound(minHeight: Int, maxHeight: Int, minWidth: Int, maxWidth: Int): Boolean {
        return row < minHeight || row >= maxHeight || col < minWidth || col >= maxWidth
    }
}
