package minesweeper.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CellsTest {
    @Test
    internal fun `높이와 너비가 0보다 크다`() {
        assertThrows<IllegalArgumentException> {
            Cells(listOf(Cell(), Cell(bomb = true)), 3)
        }
    }

    @Test
    internal fun `지뢰가 존재해야 한다`() {
        assertThrows<IllegalArgumentException> {
            Cells(listOf(Cell(), Cell()), 3)
        }
    }
}
