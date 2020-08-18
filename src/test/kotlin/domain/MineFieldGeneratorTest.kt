package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineFieldGeneratorTest {

    /**
     *      1 2 *
     *      * 3 2
     *      2 * 1
     */
    @Test
    fun `전체 필드를 제대로 만드는지 확인`() {
        // given
        val mineFieldGenerator = MineFieldGenerator(object : MinePositionsSelectStrategy {
            override fun getMinePositionsFrom(positions: List<Position>, count: Int): List<Position> {
                return listOf(Position.of(3, 1), Position.of(1, 2), Position.of(2, 3))
            }
        })
        val expectedMineField = MineField(
            Rectangle(3, 3),
            listOf(
                NormalBlock(1, 1, 1),
                NormalBlock(2, 1, 2),
                Mine(3, 1),
                Mine(1, 2),
                NormalBlock(2, 2, 3),
                NormalBlock(3, 2, 2),
                NormalBlock(1, 3, 2),
                Mine(2, 3),
                NormalBlock(3, 3, 1)
            )
        )

        // when
        val mineField = mineFieldGenerator.create(Rectangle(3, 3), 3)

        // then
        assertThat(mineField).isEqualTo(expectedMineField)
    }
}
