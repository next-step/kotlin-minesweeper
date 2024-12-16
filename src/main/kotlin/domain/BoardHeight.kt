package domain

@JvmInline
value class BoardHeight(val value: Int) {
    init {
        require(value > 0) { INVALID_ROW_SIZE }
    }

    operator fun compareTo(other: Int): Int = value.compareTo(other)

    operator fun times(other: BoardWidth): Int = value * other.value

    companion object {
        private const val INVALID_ROW_SIZE = "높이는 1이상입니다."
    }
}
