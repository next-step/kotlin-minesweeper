package minesweeper.domain

@JvmInline
value class Width(val value: Int) {

    init {
        require(value >= 1) { WIDTH_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE }
    }

    companion object {
        const val WIDTH_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "너비는 1이상이어야 합니다."
        const val WIDTH_NUMBER_FORMAT_EXCEPTION_MESSAGE = "너비는 양의정수여야 합니다"

        fun from(value: String): Width {
            return Width(value.toIntOrNull() ?: throw NumberFormatException(WIDTH_NUMBER_FORMAT_EXCEPTION_MESSAGE))
        }

        fun from(value: Int): Width {
            return Width(value)
        }
    }
}
