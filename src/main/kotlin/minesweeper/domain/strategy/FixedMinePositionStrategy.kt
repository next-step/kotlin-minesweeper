package minesweeper.domain.strategy

import minesweeper.domain.Positions
import minesweeper.domain.minemap.MineMapConfig

/**
 * ### 지뢰 매설 위치가 특정 위치로 고정된 전략입니다
 */
class FixedMinePositionStrategy(
    private val mineMapConfig: MineMapConfig,
    private val minePositions: Positions,
) : MinePositioningStrategy {

    init {
        minePositions.forEach {
            require(it.y < mineMapConfig.height) {
                "minePosition y must be less than mineMap height, actual : $it.y"
            }
            require(it.x < mineMapConfig.width) {
                "minePosition x must be less than mineMap height, actual : $it.y"
            }
        }
        require(minePositions.size == mineMapConfig.mineCount) {
            "minePosition size must be equal to mineCount, actual ${minePositions.size}"
        }
    }

    override fun getMinePositions(): Positions {
        return minePositions
    }
}
