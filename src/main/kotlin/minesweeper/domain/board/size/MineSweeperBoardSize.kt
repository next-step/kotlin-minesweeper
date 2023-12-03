package minesweeper.domain.board.size

data class MineSweeperBoardSize(
    val width: Int,
    val height: Int,
) {
    init {
        require(width > 0) { INVALID_VALUE }
        require(height > 0) { INVALID_VALUE }
    }

    fun getBoardFullSize(): Int = height * width

    companion object {
        private const val INVALID_VALUE = "잘못된 값을 입력하셨습니다."
    }
}
