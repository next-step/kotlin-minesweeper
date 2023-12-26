package mineswipper.domain.map.util

import mineswipper.domain.map.position.Positions
import mineswipper.domain.map.position.Size

class PositionFactory(
    private val minePositionStrategy: MinePositionStrategy
) {
    fun generateMinePositions(size: Size, mineAmount: Int): Positions {
        require(size.width >= mineAmount && size.height >= mineAmount) { MINE_AMOUNT_VALID_MESSAGE }

        return minePositionStrategy.createMinePosition(size, mineAmount)
    }

    companion object {
        private const val MINE_AMOUNT_VALID_MESSAGE: String = "마인 갯수는 높이와 넓이보다 클 수 없습니다."
    }
}
