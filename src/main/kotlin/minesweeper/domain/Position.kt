package minesweeper.domain

data class Position(val row: Int, val column: Int) {

    fun getNearPositions(): List<Position> =
        (this.row - 1..this.row + 1).flatMap { row ->
            (this.column - 1..this.column + 1).map { column ->
                Position(row, column)
            }
        }.filter { it != this }

    companion object {
        fun getAllPositions(height: Int, width: Int): List<Position> =
            (0 until height).flatMap { row ->
                (0 until width).map { column ->
                    Position(row, column)
                }
            }

        fun getRandomPositions(height: Int, width: Int, mineCount: Int) = (0 until height * width).shuffled()
            .map { Position(it / width, it % width) }
            .take(mineCount)
    }
}


