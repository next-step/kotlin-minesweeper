package minesweeper.domain

import io.kotest.matchers.ints.shouldBeLessThan
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
}
