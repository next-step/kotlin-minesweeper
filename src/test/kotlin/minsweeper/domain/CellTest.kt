package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `Cell을 오픈할 수 있다`() {
        // given
        val cell = Cell.Mine

        // when
        cell.open()

        // then
        assertThat(cell.isOpened).isTrue()
    }

}