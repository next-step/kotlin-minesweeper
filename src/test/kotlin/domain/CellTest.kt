package domain

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class CellTest {
    @Test
    fun `CellType 이 Mine 일때 isMine true`() {
        val cell = Cell(CellType.MINE)
        val actual = cell.isMine()

        assertTrue(actual)
    }

    @Test
    fun `CellType 이 GENERAL 일때 isMine false`() {
        val cell = Cell(CellType.GENERAL)
        val actual = cell.isMine()

        assertFalse(actual)
    }
}
