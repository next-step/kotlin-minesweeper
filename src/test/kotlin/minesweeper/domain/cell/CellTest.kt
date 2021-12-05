package minesweeper.domain.cell

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `Cell이 Block인지 Mine인지 구분할 수 있다`() {
        // given
        val block = Block
        val mine = Mine

        // then
        assertThat(block.isMine()).isFalse
        assertThat(mine.isMine()).isTrue
    }
}
