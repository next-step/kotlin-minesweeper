package minesweeper.domain

class GameBoardSize(
    val width: Int,
    val height: Int
) {
    init {
        require(width > 0 && height > 0) { "넓이와 높이는 모두 0보다 커야합니다." }
    }
}
