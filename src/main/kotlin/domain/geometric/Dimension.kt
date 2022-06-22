package domain.geometric

data class Dimension(
    val width: Int,
    val height: Int,
) {
    val area: Int = width * height

    init {
        require(width > 0 && height > 0) {
            "너비와 높이는 0보다 큰 값이어야 합니다"
        }
    }
}
