package minesweeper.domain

data class Position(val row: Row, val column: Column) : Comparable<Position> {

    override fun compareTo(other: Position): Int {
        return compareValuesBy(
            this,
            other,
            { it.column },
            { it.row }
        )
    }

    fun isRowStart(): Boolean = row.isStart

    companion object {

        fun from(row: Row, column: Column): Position {
            return Position(row, column)
        }
    }
}
