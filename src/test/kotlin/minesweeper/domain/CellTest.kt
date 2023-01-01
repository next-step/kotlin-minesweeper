package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CellTest {

    @Test
    fun `폭탄일 경우 ⎈ 가 출력된다`() {
        // given
        val cell = Cell(0, true)

        // when
        val actual = cell.toString()

        // then
        val expected = "⎈ "
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `폭탄이 아닌 경우 C 가 출력된다`() {
        // given
        val cell = Cell(0, false)

        // when
        val actual = cell.toString()

        // then
        val expected = "C "
        assertThat(actual).isEqualTo(expected)
    }
}
