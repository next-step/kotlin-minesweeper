package minesweeper.domain.strategy

import minesweeper.domain.minemap.MineMapConfig
import minesweeper.domain.Positions

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
