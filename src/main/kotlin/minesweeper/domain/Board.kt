package minesweeper.domain

class Board(
    height: Int,
    width: Int,
) {
    val points: Array<IntArray>

    init {
        require(height > ZERO && width > ZERO) { BOARD_INIT_VALUE_EXCEPTION }
        points = Array(height, { IntArray(width) })
    }

    companion object {
        private const val ZERO = 0
        private const val BOARD_INIT_VALUE_EXCEPTION = "보드의 높이, 너비는 양수 값이어야 합니다."
    }
}
