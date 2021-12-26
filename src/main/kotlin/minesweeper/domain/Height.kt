package minesweeper.domain

@JvmInline
value class Height(val value: Int) {

    init {
        require(value >= 1) { HEIGHT_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE }
    }

    companion object {
        const val HEIGHT_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "높이는 1이상이어야 합니다."
        const val HEIGHT_NUMBER_FORMAT_EXCEPTION_MESSAGE = "높이는 양의정수여야 합니다"

        fun from(value: String): Height {
            return from(value.toIntOrNull() ?: throw NumberFormatException(HEIGHT_NUMBER_FORMAT_EXCEPTION_MESSAGE))
        }

        fun from(value: Int): Height {
            return Height(value)
        }
    }
}
