package mineswipper.domain.map

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import mineswipper.domain.map.util.MinePositionStrategy
import mineswipper.domain.map.util.PositionFactory
import org.junit.jupiter.api.Test

class PositionFactoryTest {

    @Test
    fun `높이와 넓이와 마인 갯수로 마인의 위치를 만든다`() {
        val positionFactory = PositionFactory(TestStrategy())
        val height = 10
        val width = 10
        val mineAmount = 10

        val positions: List<Position> = positionFactory.generateMinePositions(
            Size(width, height),
            mineAmount
        )

        positions.containsAll(
            listOf(
                Position(1, 1),
                Position(2, 2)
            )
        ) shouldBe true
    }

    @Test
    fun `높이와 넓이보다 많이 지뢰를 만들 수 없다`() {
        val positionFactory = PositionFactory(TestStrategy())
        val height = 10
        val width = 10
        val mineAmount = 100

        shouldThrow<IllegalArgumentException> { positionFactory.generateMinePositions(Size(width, height), mineAmount) }
    }
}

class TestStrategy : MinePositionStrategy {
    override fun createMinePosition(size: Size, mineAmount: Int): List<Position> {
        return listOf(
            Position(1, 1),
            Position(2, 2)
        )
    }
}
