package minesweeper.domain.strategy

import minesweeper.domain.minemap.MineMapConfig
import minesweeper.domain.Position
import minesweeper.domain.Positions

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
