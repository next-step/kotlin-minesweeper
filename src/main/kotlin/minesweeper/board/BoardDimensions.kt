package minesweeper.board

import minesweeper.exception.BoardSizeOverException
import minesweeper.position.Position

data class BoardDimensions(
    val height: Height,
    val width: Width
) {

    fun stringToPosition(input: String): Result<Position> =
        runCatching {
            input.split(DELIMITER).let {
                validateInputSize(input, it)
                val row = it[ROW].toInt()
                val col = it[COL].toInt()
                validateSize(row, col)
                Position(row - 1, col - 1)
            }
        }

    private fun validateInputSize(input: String, splitted: List<String>) {
        require(splitted.size == INPUT_SIZE) {
            INPUT_ERROR_MESSAGE.format(input)
        }
    }

    private fun validateSize(row: Int, col: Int) {
        if(this.height < row || this.width < col || row <= 0 || col <= 0) {
            throw BoardSizeOverException(SIZE_OVER_MESSAGE.format(height.value, width.value, row, col))
        }
    }

    companion object {
        private const val ROW = 0
        private const val COL = 1
        private const val INPUT_SIZE = 2
        private const val DELIMITER = ", "
        private const val INPUT_ERROR_MESSAGE = "두 개의 정수를 입력하여 주세요. 입력한 내용:%s"
        private const val SIZE_OVER_MESSAGE = "게임판 사이즈: [높이 %d, 너비 %d], 입력한 사이즈: [높이 %d, 너비 %d]"
    }
}
