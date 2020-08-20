package domain.field

import domain.MinePositionsSelectStrategy
import domain.block.Blocks
import domain.block.Mine
import domain.block.OpenedBlock
import domain.block.Position
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
        val mineFieldGenerator =
            MineFieldGenerator(object : MinePositionsSelectStrategy {
                override fun getMinePositionsFrom(positions: List<Position>, count: Int): List<Position> {
                    return listOf(Position.of(3, 1), Position.of(1, 2), Position.of(2, 3))
                }
            })
        val expectedMineField = MineField(
            Rectangle(3, 3),
            Blocks(
                listOf(
                    OpenedBlock(1, 1, 1),
                    OpenedBlock(2, 1, 2),
                    Mine(3, 1),
                    Mine(1, 2),
                    OpenedBlock(2, 2, 3),
                    OpenedBlock(3, 2, 2),
                    OpenedBlock(1, 3, 2),
                    Mine(2, 3),
                    OpenedBlock(3, 3, 1)
                )
            )
        )

        // when
        val mineField = mineFieldGenerator.create(Rectangle(3, 3), 3)

        // then
        assertThat(mineField).isEqualTo(expectedMineField)
    }
}
