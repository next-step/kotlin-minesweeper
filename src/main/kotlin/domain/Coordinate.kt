package domain

data class Coordinate(val row: Row, val col: Col) {
    operator fun plus(offset: Coordinate): Coordinate {
        return Coordinate(row + offset.row.value, col + offset.col.value)
    }
}
