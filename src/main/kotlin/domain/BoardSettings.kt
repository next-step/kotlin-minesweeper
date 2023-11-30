package domain

import error.ErrorMessage

data class BoardSettings(val row: Int, val col: Int, val mineCount: Int) {
    init {
        require(row > 0) { ErrorMessage.EXPECT_POSITIVE_NUMBER_HEIGHT.message }
        require(col > 0) { ErrorMessage.EXPECT_POSITIVE_NUMBER_WIDTH.message }
        require(row * col > mineCount) { ErrorMessage.EXPECT_MINE_COUNT_LESS_THAN_BOARD_SIZE.message }
    }
}
