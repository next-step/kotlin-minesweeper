package domain

data class Dimension(
    val width: Int,
    val height: Int,
) {
    init {
        require(width > 0 && height > 0) { INVALID_WIDTH_HEIGHT_INPUT_MESSAGE }
    }

    companion object {
        const val INVALID_WIDTH_HEIGHT_INPUT_MESSAGE = "너비와 높이는 0보다 큰 값이어야 합니다."
    }
}
