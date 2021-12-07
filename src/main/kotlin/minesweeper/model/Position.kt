package minesweeper.model

import java.util.Objects

class Position private constructor(val row: Row, val column: Column) {

    fun top(): Position = of(column = column, row = row.decrement())

    fun topLeft(): Position = of(row = row.decrement(), column = column.decrement())

    fun topRight(): Position = of(row = row.decrement(), column = column.increment())

    fun left(): Position = of(row = row, column = column.decrement())

    fun right(): Position = of(row = row, column = column.increment())

    fun bottom(): Position = of(row = row.increment(), column = column)

    fun bottomLeft(): Position = of(row = row.increment(), column = column.decrement())

    fun bottomRight(): Position = of(row = row.increment(), column = column.increment())

    fun asDirections(): List<Position> = listOf(
        top(),
        topRight(),
        topLeft(),
        right(),
        left(),
        bottom(),
        bottomRight(),
        bottomLeft()
    ).distinct()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Position) {
            return false
        }
        return column == other.column && row == other.row
    }

    override fun hashCode(): Int = Objects.hash(column, row)

    companion object {
        private val POSITION_POOL = mutableMapOf<Pair<Row, Column>, Position>()

        fun of(row: Row, column: Column): Position {
            val key = row to column
            return POSITION_POOL[key]
                ?: Position(row, column).also { POSITION_POOL[key] = it }
        }

        fun list(width: Width, height: Height): List<Position> = List(width.value * height.value) { index ->
            val row = (index / width.value) + 1
            val column = (index % width.value) + 1
            of(Row(row), Column(column))
        }
    }
}
