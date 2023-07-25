package minesweeper.domain

class BoardSize(val width: Int, val height: Int) {
    init {
        require(width > 0) { "가로 길이는 0보다 커야 합니다." }
        require(height > 0) { "세로 길이는 0보다 커야 합니다." }
    }
}
