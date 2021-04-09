package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `지뢰가 없으면 open 한다`() {
        val cell = Cell(false)
        cell.openIfNotMine()

        assertThat(cell.isOpen).isTrue()
    }

    @Test
    fun `지뢰가 있으면 open 하지 않는다`() {
        val cell = Cell(true)
        cell.openIfNotMine()

        assertThat(cell.isOpen).isFalse()
    }
}
