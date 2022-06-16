package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `게임판의 NON_MINE 셀을 생성한다`() {
        val position = Position(5, 5)
        val cell = Cell.of(position, false)

        assertThat(cell.cellType).isEqualTo(CellType.NON_MINE)
    }

    @Test
    fun `게임판의 MINE 셀을 생성한다`() {
        val position = Position(5, 5)
        val cell = Cell.of(position, true)

        assertThat(cell.cellType).isEqualTo(CellType.MINE)
    }
}
