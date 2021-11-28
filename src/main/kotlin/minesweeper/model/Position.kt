package minesweeper.model

data class Position(val row: Row, val column: Column) {

    companion object {
        fun list(width: Width, height: Height): List<Position> = List(width.value * height.value) { index ->
            val row = (index / width.value) + 1
            val column = (index % width.value) + 1
            Position(Row(row), Column(column))
        }
    }
}
