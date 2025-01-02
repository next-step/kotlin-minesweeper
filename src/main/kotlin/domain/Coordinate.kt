package domain

data class Coordinate(val row: Row, val col: Col) {
    operator fun plus(offset: Coordinate): Coordinate {
        return Coordinate(row + offset.row, col + offset.col)
    }

    companion object {
        operator fun invoke(
            row: Int,
            col: Int,
        ): Coordinate {
            return Coordinate(Row(row), Col(col))
        }
    }
}
