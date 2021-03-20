package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MotherCellTest {
    @Test
    internal fun `높이 너비만큼 셀을 만든다`() {
        assertThat(MotherCells(10, 10).cells(0)).hasSize(100)
    }

    @Test
    internal fun `지뢰 개수만큼 생성한다`() {
        val cells: List<Cell> = MotherCells(10, 10).cells(bomb = 10)
        assertThat(cells.filter { it.bomb }).hasSize(10)
    }
}
