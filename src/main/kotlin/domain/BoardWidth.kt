package domain

@JvmInline
value class BoardWidth(val value: Int) {
    init {
        require(value > 0) { INVALID_COLUMN_SIZE }
    }

    operator fun compareTo(other: Int): Int = value.compareTo(other)

    operator fun times(other: BoardHeight): Int = value * other.value

    companion object {
        private const val INVALID_COLUMN_SIZE = "너비는 1이상입니다."
    }
}
