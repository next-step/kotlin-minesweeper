package minesweeper.model.board.coordinate

data class Position(override val row: Int, override val column: Int) : Coordinate {

    val surroundPositions: List<Position> by lazy {
        surroundPositionMask.map { this + it }
    }

    operator fun plus(other: Position) = Position(
        row = this.row + other.row,
        column = this.column + other.column
    )

    companion object {
        private val surroundPositionMask = listOf(
            Position(row = -1, column = -1),
            Position(row = -1, column = 0),
            Position(row = -1, column = 1),

            Position(row = 0, column = -1),
            Position(row = 0, column = 1),

            Position(row = 1, column = -1),
            Position(row = 1, column = 0),
            Position(row = 1, column = 1)

        )
    }
}
