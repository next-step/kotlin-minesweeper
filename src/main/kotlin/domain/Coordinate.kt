package domain

data class Coordinate(val row: Row, val col: Col) {
    operator fun plus(offset: Coordinate): Coordinate {
        return Coordinate(row + offset.row.value, col + offset.col.value)
    }

    companion object {
        operator fun invoke(
            row: Int,
            col: Int,
        ) = Coordinate(Row(row), Col(col))
    }
}
