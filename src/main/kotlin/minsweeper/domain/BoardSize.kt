package minsweeper.domain

data class BoardSize(
    val height: Int,
    val width: Int,
) {
    val area: Int = height * width

    init {
        require(width > 0) { WIDTH_EXCEPTION }
        require(height > 0) { HEIGHT_EXCEPTION }
    }

    companion object {
        private const val WIDTH_EXCEPTION = "너비는 0보다 커야합니다"
        private const val HEIGHT_EXCEPTION = "높이는 0보다 커야합니다"
    }
}
