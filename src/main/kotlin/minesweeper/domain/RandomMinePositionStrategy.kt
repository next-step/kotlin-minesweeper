package minesweeper.domain

class RandomMinePositionStrategy(
    private val mineMapConfig: MineMapConfig,
) : MinePositioningStrategy {

    override fun getMinePositions(): Positions {
        return Positions(
            Position.all(mineMapConfig.width, mineMapConfig.height)
            .shuffled()
            .take(mineMapConfig.mineCount)
        )
    }
}
