package domain

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RowTest {
    @Test
    fun `Cell 이 지뢰일경우 true`() {
        val row = Row(
            listOf(Cell(CellType.MINE))
        )

        val actual = row.isMine(0)

        assertTrue(actual)
    }

    @Test
    fun `Cell 이 지뢰가 아닐경우 false`() {
        val row = Row(
            listOf(Cell(CellType.GENERAL))
        )

        val actual = row.isMine(0)

        assertFalse(actual)
    }
}
