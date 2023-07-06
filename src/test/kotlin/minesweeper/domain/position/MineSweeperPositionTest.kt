package minesweeper.domain.position

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MineSweeperPositionTest {

    @Test
    fun `주변에 지뢰가 0개인 경우`() {
        val position = Position(x = 1, y = 1)
        val minePositions = Positions(
            listOf(
                Position(x = 3, y = 3),
                Position(x = 2, y = 3),
            ),
        )

        val emptyPosition = EmptyPosition(position = position, minePositions = minePositions)

        emptyPosition.calculateAroundMineQuantity() shouldBe 0
    }

    @ParameterizedTest
    @MethodSource("getAroundMineQuantityTestData")
    fun `주변에 지뢰가 1개 이상인 경우`(position: Position, minePositions: Positions, expected: Int) {
        val emptyPosition = EmptyPosition(position = position, minePositions = minePositions)

        emptyPosition.calculateAroundMineQuantity() shouldBe expected
    }

    @Test
    fun `방문하면 방문 여부가 true 로 업데이트 된다`() {
        val position = Position(x = 1, y = 1)
        val emptyPosition = EmptyPosition(position = position, minePositions = Positions(emptyList()))

        emptyPosition.visit()

        emptyPosition.isVisit() shouldBe true
    }

    @Test
    fun `방문하지 않으면 방문 여부는 false 이다`() {
        val position = Position(x = 1, y = 1)
        val emptyPosition = EmptyPosition(position = position, minePositions = Positions(emptyList()))

        emptyPosition.isVisit() shouldBe false
    }

    companion object {

        @JvmStatic
        fun getAroundMineQuantityTestData(): List<Arguments> = PositionHelper.getAroundMineQuantityTestData()
    }
}
