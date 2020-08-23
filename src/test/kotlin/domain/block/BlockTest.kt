package domain.block

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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
        val block: Block = OpenedBlock(Position.of(0, 0), 1)

        // then
        assertAll(
            { assertThat(block.isMine()).isFalse() },
            { assertThat(block.getMinesCount()).isEqualTo(1) }
        )
    }

    // @Test
    // fun `칸을 열 수 있다`() {
    //     // given
    //     val block: Block = OpenedBlock(0, 0, 0)
    //
    //     // when
    //     val openedBlock: Block = block.open()
    //
    //     // then
    //     assertThat(openedBlock.isClose).isFalse()
    // }

    @CsvSource("1,1,true", "0,0,false")
    @ParameterizedTest
    fun `칸이 해당 위치에 있는지 확인`(x: Int, y: Int, isAtPosition: Boolean) {
        // given
        val block: Block = OpenedBlock(1, 1, 0)
        val position = Position.of(x, y)

        // then
        assertThat(block.isAt(position)).isEqualTo(isAtPosition)
    }
}
