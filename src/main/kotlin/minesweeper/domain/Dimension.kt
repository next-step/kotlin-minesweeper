package minesweeper.domain

data class Dimension(val height: Int, val width: Int) {
    init {
        require(height > 0) { "높이는 0 보다 커야 합니다" }
        require(width > 0) { "너비는 0보다 커야 합니다" }
    }

    fun totalCells(): Int = height * width
}
