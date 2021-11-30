package minesweeper.domain

data class Position(val row: Row, val column: Column) : Comparable<Position> {

    fun around(): Positions {
        val positions = Positions.from(rowRange = row.around, columnRange = column.around)
        return positions - this
    }

    override fun compareTo(other: Position): Int {
        return compareValuesBy(this, other, Position::row, Position::column)
    }

    companion object {

        fun from(row: Int, column: Int): Position {
            return Position(Row(row), Column(column))
        }
    }
}

@JvmInline
value class Column(val value: Int) : Comparable<Column> {

    init {
        require(value >= START_VALUE)
    }

    val around: IntRange
        get() = (value - AROUND_VALUE)..(value + AROUND_VALUE)

    override fun compareTo(other: Column): Int {
        return compareValues(value, other.value)
    }

    companion object {
        const val START_VALUE = 1
        private const val AROUND_VALUE = 1
    }
}

@JvmInline
value class Row(val value: Int) : Comparable<Row> {

    init {
        require(value >= START_VALUE)
    }

    val around: IntRange
        get() = (value - AROUND_VALUE)..(value + AROUND_VALUE)

    override fun compareTo(other: Row): Int {
        return compareValues(value, other.value)
    }

    companion object {
        const val START_VALUE = 1
        private const val AROUND_VALUE = 1
    }
}
