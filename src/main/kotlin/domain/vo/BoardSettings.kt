package domain.vo

import error.ErrorMessage

data class BoardSettings(val height: Int, val width: Int, val mineCount: Int) {
    init {
        require(height > 0) { ErrorMessage.EXPECT_POSITIVE_NUMBER_HEIGHT.message }
        require(width > 0) { ErrorMessage.EXPECT_POSITIVE_NUMBER_WIDTH.message }
        require(height * width > mineCount) { ErrorMessage.EXPECT_MINE_COUNT_LESS_THAN_BOARD_SIZE.message }
    }
}
