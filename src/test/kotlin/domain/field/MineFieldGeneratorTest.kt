package domain.field

import domain.MinePositionsSelectStrategy
import domain.block.Blocks
import domain.block.ClosedBlock
import domain.block.Mine
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
                    ClosedBlock(1, 1),
                    ClosedBlock(2, 1),
                    Mine(3, 1),
                    Mine(1, 2),
                    ClosedBlock(2, 2),
                    ClosedBlock(3, 2),
                    ClosedBlock(1, 3),
                    Mine(2, 3),
                    ClosedBlock(3, 3)
                )
            )
        )

        // when
        val mineField = mineFieldGenerator.create(Rectangle(3, 3), 3)

        // then
        assertThat(mineField).isEqualTo(expectedMineField)
    }
}
