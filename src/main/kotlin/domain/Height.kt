package domain

@JvmInline
value class Height(
    val value: Int,
) {
    init {
        require(value > THRESHOLD) { NOT_POSITIVE_HEIGHT }
    }

    companion object {
        private const val THRESHOLD = 0
        private const val NOT_POSITIVE_HEIGHT = "높이는 양수를 입력해야합니다."
    }
}
