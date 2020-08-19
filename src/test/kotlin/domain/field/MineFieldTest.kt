package domain.field

import domain.block.Blocks
import domain.block.Mine
import domain.block.NormalBlock
import domain.block.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineFieldTest {

    /**
     *  0   1   1        0   1   □
     *  1   2   *   =>   1   2   □
     *  *   2   1        □   □   □
     */
    @Test
    fun `지뢰가 아닌 칸 열었을 때 인접 칸 열리는지 확인`() {
        // given
        val blocks = Blocks(
            listOf(
                NormalBlock(1, 1, 0),
                NormalBlock(2, 1, 1),
                NormalBlock(3, 1, 1),
                NormalBlock(1, 2, 1),
                NormalBlock(2, 2, 2),
                Mine(3, 2),
                Mine(1, 3),
                NormalBlock(2, 3, 2),
                NormalBlock(3, 3, 1)
            )
        )
        val mineField = MineField(Rectangle(3, 3), blocks)
        val expectedBlocks = Blocks(
            listOf(
                NormalBlock(1, 1, 0, false),
                NormalBlock(2, 1, 1, false),
                NormalBlock(3, 1, 1),
                NormalBlock(1, 2, 1, false),
                NormalBlock(2, 2, 2, false),
                Mine(3, 2),
                Mine(1, 3),
                NormalBlock(2, 3, 2),
                NormalBlock(3, 3, 1)
            )
        )
        val expectedMineField = MineField(Rectangle(3, 3), expectedBlocks)

        // then
        assertThat(mineField.open(Position.of(1, 1))).isEqualTo(expectedMineField)
    }
}
