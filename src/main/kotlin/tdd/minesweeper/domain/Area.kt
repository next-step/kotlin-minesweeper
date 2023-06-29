package tdd.minesweeper.domain

data class Area(
    val width: Int,
    val height: Int
) {
    val size = width * height

    init {
        require(width > 0) {
            "적절한 너비 크기가 아닙니다. Invalid Widht: $width"
        }

        require(height > 0) {
            "적절한 높이 크기가 아닙니다. Invalid Height: $height"
        }
    }
}
