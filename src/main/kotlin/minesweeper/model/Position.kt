package minesweeper.model

data class Position(val row: Row, val column: Column) {

    companion object {
        fun of(row: Int, column: Int) = Position(Row(row), Column(column))
    }
}
