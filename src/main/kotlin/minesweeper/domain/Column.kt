package minesweeper.domain

@JvmInline
value class Column(val value: Int) : Comparable<Column> {

    override fun compareTo(other: Column): Int {
        return compareValues(value, other.value)
    }

    init {
        require(value >= 0) { COLUMN_ILLEGAL_ARGUMENTS_EXCEPTION }
    }

    companion object {
        const val COLUMN_ILLEGAL_ARGUMENTS_EXCEPTION = "column은 0이상이어야 합니다."

        fun from(value: Int): Column {
            return Column(value)
        }
    }
}
