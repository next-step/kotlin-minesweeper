package minesweeper.domain

@JvmInline
value class Row(val value: Int) : Comparable<Row> {

    val isStart: Boolean
        get() = value == START_ROW_NUM

    init {
        require(value >= 0) { ROW_ILLEGAL_ARGUMENTS_EXCEPTION }
    }

    override fun compareTo(other: Row): Int {
        return compareValues(value, other.value)
    }

    companion object {
        const val START_ROW_NUM = 0
        const val ROW_ILLEGAL_ARGUMENTS_EXCEPTION = "row는 0이상이어야 합니다."

        fun from(value: Int): Row {
            return Row(value)
        }
    }
}
