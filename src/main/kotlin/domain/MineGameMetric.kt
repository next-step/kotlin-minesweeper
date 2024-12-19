package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH

class MineGameMetric(val boardHeightSize: Int, val boardWidthSize: Int, val mineCount: Int) {
    init {
        require(boardHeightSize > MINIMUM_HEIGHT)
        require(boardWidthSize > MINIMUM_WIDTH)
        require(boardWidthSize * boardWidthSize >= mineCount)
    }
}
