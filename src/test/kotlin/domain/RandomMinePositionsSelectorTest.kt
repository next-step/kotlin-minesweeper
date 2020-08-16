package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class RandomMinePositionsSelectorTest {
    @Test
    fun `원하는 개수의 원하는 지뢰의 위치를 뱉는지 확인`() {
        // given
        val positions = listOf(
            Position(1, 1),
            Position(2, 1),
            Position(2, 1),
            Position(2, 2)
        )
        val expectedMinePositions = listOf(Position(1, 1), Position(2, 2))
        val mineSelector = object : MinePositionsSelectStrategy {
            override fun getMinePositionsFrom(positions: List<Position>, count: Int): List<Position> {
                return expectedMinePositions
            }
        }

        // when
        val minePositions = mineSelector.getMinePositionsFrom(positions, 2)

        // then
        assertThat(minePositions).isEqualTo(expectedMinePositions)
    }

    @Test
    fun `위치의 개수보다 지뢰의 개수가 클 수 없다`() {
        // given
        val mineGenerator = RandomMinePositionsSelector()

        // then
        assertThatThrownBy { mineGenerator.getMinePositionsFrom(emptyList(), 1) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("전체 위치의 개수(0) 보다 지뢰의 개수(1)가 같거나 많을 수 없습니다.")
    }
}
