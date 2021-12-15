package minesweeper.domain

data class Position(val row: Row, val column: Column) {

    companion object {

        fun from(row: Row, column: Column): Position {
            return Position(row, column)
        }
    }
}
