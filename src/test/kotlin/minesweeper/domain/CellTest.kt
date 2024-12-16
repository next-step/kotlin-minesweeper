package minesweeper.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    @DisplayName("지뢰는 * 표시로 나타난다")
    fun `mine cell identifies as mine`() {
        val cell = Cell(Mine())
        assertTrue(cell.isMine())
        assertEquals("*", cell.toString())
    }

    @Test
    @DisplayName("지뢰가 아니라면 C 표시로 나타난다")
    fun `empty cell identifies as not mine`() {
        val cell = Cell(Empty())
        assertTrue(cell.isMine().not())
        assertEquals("C", cell.toString())
    }
}