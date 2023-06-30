package minesweeper.domain.board

class BoardRange(private val height: Int, private val width: Int) {

    init {
        require(height >= 1) { "높이는 1 이상이어야 합니다." }
        require(width >= 1) { "너비는 1 이상이어야 합니다." }
    }
}
