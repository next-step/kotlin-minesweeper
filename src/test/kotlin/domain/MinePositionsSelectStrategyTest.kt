package domain

import domain.strategy.MinePositionsSelectStrategy
import domain.strategy.RandomMinePositionsSelectStrategy
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MinePositionsSelectStrategyTest {

    private val fakePositions = Position.createAll(Rectangle(3, 3))

    @ValueSource(ints = [9, 11])
    @ParameterizedTest
    fun `지뢰 수가 전체 크기보다 같거나 크면 예외 발생`(mineCount: Int) {
        val positionsSelector = RandomMinePositionsSelectStrategy()
        shouldThrow<IllegalArgumentException> {
            positionsSelector.getMinePositions(fakePositions, mineCount)
        }
    }

    @Test
    fun `지정 지뢰 위치를 정상 반환`() {
        val minePositions = listOf(Position(0, 1), Position(1, 1), Position(2, 3))
        val positionsSelector = object : MinePositionsSelectStrategy {
            override fun getMinePositions(positions: List<Position>, mineCount: Int): List<Position> {
                return minePositions
            }
        }

        positionsSelector.getMinePositions(fakePositions, minePositions.size) shouldBe minePositions
    }
}
