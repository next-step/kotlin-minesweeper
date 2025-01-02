package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_MINE_COUNT
import constants.MineSweeperConstants.MINIMUM_WIDTH

data class MineSweeperMetric(val mineBoardHeight: Int, val mineBoardWidth: Int, val mineCount: Int) {
    init {
        require(mineBoardHeight >= MINIMUM_HEIGHT) { "높이는 $MINIMUM_HEIGHT 이상입니다. value: $mineBoardHeight" }
        require(mineBoardWidth >= MINIMUM_WIDTH) { "너비는 $MINIMUM_WIDTH 이상입니다. value: $mineBoardWidth" }
        require(
            mineCount >= MINIMUM_MINE_COUNT && mineBoardHeight * mineBoardWidth >= mineCount,
        ) { "지뢰 개수는 전체 칸의 개수보다 많을 수 없습니다. row: $mineBoardHeight, col: $mineBoardWidth value: $mineCount" }
    }
}
