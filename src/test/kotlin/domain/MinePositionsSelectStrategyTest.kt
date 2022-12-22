package domain

import domain.strategy.RandomMinePositionsSelectStrategy
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainAll
import org.junit.jupiter.api.Test

class MinePositionsSelectStrategyTest {

    private val fakePositions = Rectangle(Width(2), Height(2)).toPositions()

    @Test
    fun `지뢰 수가 전체 크기보다 크면 예외 발생`() {
        val positionsSelector = RandomMinePositionsSelectStrategy()
        shouldThrow<IllegalArgumentException> {
            positionsSelector.getMinePositions(fakePositions, MineCount(fakePositions.size + 1))
        }
    }

    @Test
    fun `위치 수와 지뢰 수가 같으면 전체가 반환된다`() {
        val minePositions = listOf(
            Position.of(0, 0), Position.of(0, 1), Position.of(1, 0), Position.of(1, 1)
        )
        val positionsSelector = RandomMinePositionsSelectStrategy()
        positionsSelector.getMinePositions(fakePositions, MineCount(minePositions.size)) shouldContainAll minePositions
    }
}
