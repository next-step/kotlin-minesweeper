package mineswipper.domain.map.util

import mineswipper.domain.map.Position
import mineswipper.domain.map.Size

class PositionFactory(
    private val minePositionStrategy: MinePositionStrategy
) {

    fun generateMinePositions(height: Int, width: Int, mineAmount: Int): List<Position> {
        return (0 until  mineAmount).map {
            val x = (0 until height).random()
            val y = (0 until width).random()
            Position(x, y)
        }
    }

    fun generateMinePositions(size: Size, mineAmount: Int): List<Position> {
        require(size.width >= mineAmount && size.height >= mineAmount) { MINE_AMOUNT_VALID_MESSAGE }

        return minePositionStrategy.createMinePosition(size, mineAmount)
    }

    companion object {
        private const val MINE_AMOUNT_VALID_MESSAGE: String = "마인 갯수는 높이와 넓이보다 클 수 없습니다."
    }
}
