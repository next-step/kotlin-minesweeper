package domain.block

import domain.GameOverException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BlocksTest {

    @Test
    fun `해당 위치가 지뢰인지 확인`() {
        // given
        val blocks = Blocks(
            listOf(
                Mine(0, 0),
                NormalBlock(1, 0, 1),
                NormalBlock(0, 1, 1),
                NormalBlock(1, 1, 1)
            )
        )

        // then
        assertThatThrownBy { blocks.open(Position.of(0, 0)) }
            .isInstanceOf(GameOverException::class.java)
            .hasMessage("해당 칸은 지뢰입니다!")
    }

    @Test
    fun `없는 위치의 Block을 찾는경우 Exception`() {
        // given
        val blocks = Blocks(
            listOf(
                Mine(0, 0),
                NormalBlock(1, 0, 1),
                NormalBlock(0, 1, 1),
                NormalBlock(1, 1, 1)
            )
        )
        val invalidPosition = Position.of(2, 2)

        // then
        assertThatThrownBy { blocks.open(invalidPosition) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${invalidPosition}에 해당하는 칸을 찾을 수 없습니다.")
    }

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

        // then
        assertThat(blocks.open(Position.of(1, 1))).isEqualTo(expectedBlocks)
    }

    @Test
    fun `지뢰가 아닌 모든 칸이 열렸는지 확인`() {
        // given
        val blocks = Blocks(
            listOf(
                Mine(0, 0),
                NormalBlock(1, 0, 1, false),
                NormalBlock(0, 1, 1, false),
                NormalBlock(1, 1, 1, false)
            )
        )

        // then
        assertThat(blocks.isAllNormalBlocksOpened()).isTrue()
    }
}
