package minesweeper

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import minesweeper.domain.MineMapConfig
import minesweeper.domain.RandomMinePositionStrategy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MinePositionStrategyTest {

    @ParameterizedTest
    @CsvSource(
        "7, 4",
        "10, 10"
    )
    internal fun `지뢰 매설 위치는 지도의 내부로 한정되야 한다`(
        height: Int,
        width: Int,
    ) {
        val sut = RandomMinePositionStrategy(MineMapConfig(height, width, 2))
        val minePositions = sut.getMinePositions()
        minePositions.forEach {
            it.y shouldBeLessThanOrEqual height
            it.x shouldBeLessThanOrEqual width
        }
    }

    @Test
    internal fun `지뢰 매설 위치 개수는 지뢰의 개수와 같다`() {
        val sut = RandomMinePositionStrategy(MineMapConfig(10, 10, 10))
        val minePositions = sut.getMinePositions()
        minePositions shouldHaveSize 10
    }
}
