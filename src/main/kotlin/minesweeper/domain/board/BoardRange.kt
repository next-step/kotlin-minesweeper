package minesweeper.domain.board

class BoardRange(private val height: Int, private val width: Int) {

    init {
        require(height >= RANGE_MIN) { "높이는 1 이상이어야 합니다." }
        require(width >= RANGE_MIN) { "너비는 1 이상이어야 합니다." }
    }

    companion object {
        private const val RANGE_MIN = 1
    }
}
