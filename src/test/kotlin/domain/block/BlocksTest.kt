package domain.block

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
            .isInstanceOf(IllegalArgumentException::class.java)
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
}
