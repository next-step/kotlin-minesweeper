package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH

class MineGameMetric(val boardHeightSize: Int, val boardWidthSize: Int, val mineCount: Int) {
    init {
        require(boardHeightSize > MINIMUM_HEIGHT) { "높이는 ${MINIMUM_HEIGHT}이상 입력해주세요" }
        require(boardWidthSize > MINIMUM_WIDTH) { "너비는 ${MINIMUM_WIDTH}이상 입력해주세요" }
        require(boardWidthSize * boardWidthSize >= mineCount) { "지뢰 개수는 ${boardWidthSize * boardWidthSize}이상 입력해주세요" }
    }

    fun isOutOfMineBoard(coordinate: Coordinate): Boolean {
        return Row(MINIMUM_HEIGHT) > coordinate.row || coordinate.row > Row(boardHeightSize) ||
            Col(MINIMUM_WIDTH) > coordinate.col || coordinate.col > Col(boardWidthSize)
    }
}
