package minesweeper.domain

@JvmInline
value class Row(val value: Int) {

    init {
        require(value >= 0) { ROW_ILLEGAL_ARGUMENTS_EXCEPTION }
    }

    companion object {
        const val ROW_ILLEGAL_ARGUMENTS_EXCEPTION = "row는 0이상이어야 합니다."

        fun from(value: Int): Row {
            return Row(value)
        }
    }
}
