package minesweeper.model

data class Position(val row: Row, val column: Column) {

    fun top(): Position = copy(row = row.decrement())

    fun topLeft(): Position = Position(row = row.decrement(), column = column.decrement())

    fun topRight(): Position = Position(row = row.decrement(), column = column.increment())

    fun left(): Position = copy(column = column.decrement())

    fun right(): Position = copy(column = column.increment())

    fun bottom(): Position = copy(row = row.increment())

    fun bottomLeft(): Position = Position(row = row.increment(), column = column.decrement())

    fun bottomRight(): Position = Position(row = row.increment(), column = column.increment())

    companion object {
        fun list(width: Width, height: Height): List<Position> = List(width.value * height.value) { index ->
            val row = (index / width.value) + 1
            val column = (index % width.value) + 1
            Position(Row(row), Column(column))
        }
    }
}
