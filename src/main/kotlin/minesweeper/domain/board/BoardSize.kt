package minesweeper.domain.board

class BoardSize(val width: Int, val height: Int) {

    init {
        require(width > 0) { WIDTH_SIZE_ZERO }
        require(height > 0) { HEIGHT_SIZE_ZERO }
    }

    companion object {
        fun of(width: Int, height: Int): BoardSize = BoardSize(width, height)
        private const val WIDTH_SIZE_ZERO = "너비가 0입니다."
        private const val HEIGHT_SIZE_ZERO = "높이가 0입니다."
    }
}
