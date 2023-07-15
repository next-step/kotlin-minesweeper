package minesweeper.domain

/**
 * ### 지뢰를 어느 위치에 매설할지 결정하는 전략입니다.
 */
interface MinePositionStrategy {
    fun getMinePositions(): Positions
}

class RandomMinePositionStrategy(
    private val mineMapConfig: MineMapConfig
) : MinePositionStrategy {

    override fun getMinePositions(): Positions {
        return (0 until mineMapConfig.height).flatMap { y ->
            (0 until mineMapConfig.width).map { x ->
                Position(x, y)
            }
        }.shuffled()
            .take(mineMapConfig.mineCount)
            .let { Positions(it) }
    }
}

class FixedMinePositionStrategy(
    private val mineMapConfig: MineMapConfig,
    private val minePositions: Positions,
) : MinePositionStrategy {

    init {
        minePositions.forEach {
            require(it.y <= mineMapConfig.height)
            require(it.x <= mineMapConfig.width)
        }
        require(minePositions.size == mineMapConfig.mineCount)
    }

    override fun getMinePositions(): Positions {
        return minePositions
    }
}
