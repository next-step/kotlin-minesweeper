package minesweeper.domain

data class Position(val row: Int, val column: Int) {
    fun rangeIn(
        height: Int,
        width: Int,
    ): Boolean = this.row in 0 until height && this.column in 0 until width

    fun getNearPositions(): List<Position> =
        (this.row - 1..this.row + 1).flatMap { row ->
            (this.column - 1..this.column + 1).map { column ->
                Position(row, column)
            }
        }
}


