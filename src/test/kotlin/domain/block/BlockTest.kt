package domain.block

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlockTest {

    @Test
    fun `지뢰칸 만들기`() {
        // given
        val block: Block = Mine(Position.of(0, 0))

        // then
        assertThat(block.isMine()).isTrue()
    }

    @Test
    fun `지뢰는 주변 지뢰 개수를 가질 수 없음`() {
        // given
        val block: Block = Mine(1, 1)

        // then
        assertThatThrownBy { block.getMinesCount() }
            .isInstanceOf(UnsupportedOperationException::class.java)
            .hasMessage("지뢰는 주변 지뢰의 개수를 가지고 있지 않습니다.")
    }

    @Test
    fun `지뢰가 아닌 칸 만들기`() {
        // given
        val block: Block = NormalBlock(Position.of(0, 0), 1)

        // then
        assertAll(
            { assertThat(block.isMine()).isFalse() },
            { assertThat(block.getMinesCount()).isEqualTo(1) }
        )
    }

    @Test
    fun `칸을 열 수 있다`() {
        // given
        val block: Block = NormalBlock(0, 0, 0)

        // when
        val openedBlock: Block = block.open()

        // then
        assertThat(openedBlock.isClose).isFalse()
    }
}
