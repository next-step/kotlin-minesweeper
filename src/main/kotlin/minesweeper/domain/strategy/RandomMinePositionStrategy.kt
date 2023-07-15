package minesweeper.domain.strategy

import minesweeper.domain.Position
import minesweeper.domain.Positions
import minesweeper.domain.minemap.MineMapConfig

/**
 * ### 지뢰 매설 위치가 랜덤으로 결정되는 전략입니다
 */
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
