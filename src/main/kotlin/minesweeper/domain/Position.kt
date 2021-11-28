package minesweeper.domain

data class Position(val row: Row, val column: Column) : Comparable<Position> {

    override fun compareTo(other: Position): Int {
        return compareValuesBy(
            this,
            other,
            { it.row },
            { it.column },
        )
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
        require(value >= MIN_COLUMN_VALUE)
    }

    override fun compareTo(other: Column): Int {
        return compareValues(value, other.value)
    }

    companion object {
        private const val MIN_COLUMN_VALUE = 1
    }
}

@JvmInline
value class Row(val value: Int) : Comparable<Row> {

    init {
        require(value >= MIN_ROW_VALUE)
    }

    override fun compareTo(other: Row): Int {
        return compareValues(value, other.value)
    }

    companion object {
        private const val MIN_ROW_VALUE = 1
    }
}
