package domain

private const val MINIMUM_HEIGHT_EXCEPTION_MESSAGE = "높이는 0보다 커야 합니다."
private const val MINIMUM_WIDTH_EXCEPTION_MESSAGE = "너비는 0보다 커야 합니다."

@JvmInline
value class Height(val value: Int) {
    init {
        require(value > 0) { MINIMUM_HEIGHT_EXCEPTION_MESSAGE }
    }
}

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > 0) { MINIMUM_WIDTH_EXCEPTION_MESSAGE }
    }
}
