package model

import org.junit.jupiter.api.Test

internal class CellTest {
    @Test
    fun `open`() {
        val closedCell = Cell(CellType.Plain, Position(0, 0, 1))
        closedCell.open()
    }
}