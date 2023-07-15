package minesweeper.domain

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
    }

    override fun getMinePositions(): Positions {
        return minePositions
    }
}
