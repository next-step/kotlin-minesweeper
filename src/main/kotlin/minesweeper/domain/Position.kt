package minesweeper.domain

data class Position(val row: Row, val column: Column) : Comparable<Position> {

    fun around(): Positions {
        val rowRange = (row.value - AROUND_VALUE)..(row.value + AROUND_VALUE)
        val columnRange = (column.value - AROUND_VALUE)..(column.value + AROUND_VALUE)
        val aroundPositions = getAroundPositions(rowRange, columnRange)
        return Positions(aroundPositions)
    }

    private fun getAroundPositions(rowRange: IntRange, columnRange: IntRange): List<Position> {
        val positions = rowRange.flatMap { row ->
            columnRange.mapNotNull { column ->
                fromOrNull(row = row, column = column)
            }
        }
        return positions - this
    }

    override fun compareTo(other: Position): Int {
        return compareValuesBy(this, other, Position::row, Position::column)
    }

    companion object {

        private const val AROUND_VALUE = 1

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
