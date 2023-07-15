package minesweeper_tdd.domain.strategy

import minesweeper_tdd.domain.Position
import minesweeper_tdd.domain.Positions
import minesweeper_tdd.domain.minemap.MineMapConfig

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
