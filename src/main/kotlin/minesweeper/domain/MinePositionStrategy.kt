package minesweeper.domain

/**
 * ### 지뢰를 어느 위치에 매설할지 결정하는 전략입니다.
 */
interface MinePositionStrategy {
    fun getMinePositions(mineCount: Int): Positions
}

class RandomMinePositionStrategy(
    private val height: Int,
    private val width: Int
) : MinePositionStrategy {

    override fun getMinePositions(mineCount: Int): Positions {
        return (0 until height).flatMap { y ->
            (0 until width).map { x ->
                Position(x, y)
            }
        }.shuffled()
            .take(mineCount)
            .let { Positions(it) }
    }
}

class FixedMinePositionStrategy(
    private val minePositions: Positions
) : MinePositionStrategy {

    override fun getMinePositions(mineCount: Int): Positions {
        require(minePositions.size == mineCount)
        return minePositions
    }
}
