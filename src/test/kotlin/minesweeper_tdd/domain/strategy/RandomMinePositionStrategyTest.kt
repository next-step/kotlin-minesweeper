package minesweeper_tdd.domain.strategy

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeLessThan
import minesweeper_tdd.domain.minemap.MineMapConfig
import org.junit.jupiter.api.Test

internal class RandomMinePositionStrategyTest {
    @Test
    internal fun `지뢰 매설 위치는 지도의 내부로 한정되야 한다`() {
        val sut = RandomMinePositionStrategy(
            MineMapConfig(
                width = 5,
                height = 5,
                mineCount = 5 * 5
            )
        )
        val minePositions = sut.getMinePositions()
        minePositions.forEach {
            it.y shouldBeLessThan 5
            it.x shouldBeLessThan 5
        }
    }

    @Test
    internal fun `지뢰 매설 위치 개수는 지뢰의 개수와 같다`() {
        val sut = RandomMinePositionStrategy(
            MineMapConfig(
                width = 5,
                height = 5,
                mineCount = 10
            )
        )
        val minePositions = sut.getMinePositions()
        minePositions shouldHaveSize 10
    }
}
