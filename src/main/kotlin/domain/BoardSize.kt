package domain

data class BoardSize(
    val width: Int,
    val height: Int
) {
    init {
        require(width > 0) { "너비는 0보다 커야 합니다." }
        require(height > 0) { "높이는 0보다 커야 합니다." }
    }

    val area: Int
        get() = width * height
}
