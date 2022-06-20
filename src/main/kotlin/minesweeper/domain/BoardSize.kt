package minesweeper.domain

data class BoardSize(val height: Int, val width: Int) {
    init {
        require(height > 0) { "높이는 0보다 커야합니다." }
        require(width > 0) { "너비는 0보다 커야합니다." }
    }
}
