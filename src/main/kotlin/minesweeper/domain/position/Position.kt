package minesweeper.domain.position

data class Position(val row: Row, val column: Column) : Comparable<Position> {

    fun around(): Positions {
        val aroundPositions = CHANGES.mapNotNull { (rowChange, columnChange) ->
            fromOrNull(row.value + rowChange, column.value + columnChange)
        }
        return Positions(aroundPositions)
    }

    override fun compareTo(other: Position): Int {
        return compareValuesBy(this, other, Position::row, Position::column)
    }

    companion object {

        private val CHANGES = listOf(
            -1 to -1,
            -1 to 0,
            -1 to 1,
            0 to -1,
            0 to 1,
            1 to -1,
            1 to 0,
            1 to 1,
        )

        fun from(row: Int, column: Int): Position {
            return Position(Row(row), Column(column))
        }

        fun fromOrNull(row: Int, column: Int): Position? {
            return try {
                from(row = row, column = column)
            } catch (_: Exception) {
                null
            }
        }
    }
}

@JvmInline
value class Column(val value: Int) : Comparable<Column> {

    init {
        require(value >= START_VALUE)
    }

    override fun compareTo(other: Column): Int {
        return compareValues(value, other.value)
    }

    companion object {
        const val START_VALUE = 1
    }
}

@JvmInline
value class Row(val value: Int) : Comparable<Row> {

    init {
        require(value >= START_VALUE)
    }

    override fun compareTo(other: Row): Int {
        return compareValues(value, other.value)
    }

    companion object {
        const val START_VALUE = 1
    }
}
