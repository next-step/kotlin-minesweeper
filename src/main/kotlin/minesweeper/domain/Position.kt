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
