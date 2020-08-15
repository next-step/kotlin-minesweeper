package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineFieldGeneratorTest {
    @Test
    fun `전체 필드를 제대로 만드는지 확인`() {
        // given
        val mineFieldGenerator = MineFieldGenerator(object : MinePositionsSelectStrategy {
            override fun getMinePositionsFrom(positions: List<Position>, count: Int): List<Position> {
                return listOf(Position(1, 1))
            }
        })
        val expectedMineField = MineField(
            Rectangle(2, 2),
            listOf(
                Block(1, 1, true),
                Block(2, 1, false),
                Block(1, 2, false),
                Block(2, 2, false)
            )
        )
        // when
        val mineField = mineFieldGenerator.create(Rectangle(2, 2), 1)

        // then
        assertThat(mineField).isEqualTo(expectedMineField)
    }
}
