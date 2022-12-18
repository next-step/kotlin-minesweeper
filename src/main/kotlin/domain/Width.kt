package domain

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > THRESHOLD) { NOT_POSITIVE_WIDTH }
    }

    companion object {
        private const val THRESHOLD = 0
        private const val NOT_POSITIVE_WIDTH = "너비는 양수를 입력해야합니다."
    }
}
