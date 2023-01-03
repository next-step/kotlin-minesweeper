package minesweeper.domain

data class Position(val row: Int, val column: Int) {

    fun getNearPositions(): List<Position> =
        (row - 1..row + 1).flatMap { row ->
            (column - 1..column + 1).map { column ->
                Position(row, column)
            }
        }.filter { it != this }
}


