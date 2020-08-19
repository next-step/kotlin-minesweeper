package domain.block

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BlocksTest {

    @CsvSource("0,0,true", "1,1,false")
    @ParameterizedTest
    fun `해당 위치가 지뢰인지 확인`(x: Int, y: Int, isMine: Boolean) {
        // given
        val blocks = Blocks(
            listOf(
                Mine(0, 0),
                NormalBlock(1, 0, 1),
                NormalBlock(0, 1, 1),
                NormalBlock(1, 1, 1)
            )
        )
        // when
        val minePosition = Position.of(x, y)

        // then
        assertThat(blocks.isMineIn(minePosition)).isEqualTo(isMine)
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
        assertThatThrownBy { blocks.getIn(invalidPosition) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${invalidPosition}에 해당하는 칸을 찾을 수 없습니다.")
    }
}
