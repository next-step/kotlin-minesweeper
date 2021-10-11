package model

import model.cell.Cell
import model.cell.ImmutableCell
import model.position.PositivePosition
import org.junit.jupiter.api.Test

internal class CellTest {
    companion object {
        private val FAKE_CELL_IDENTITY = CellIdentity(
            CellType.Plain, AroundMineCount.ZERO, PositivePosition(
                IndexWithMax(0, 0),
                IndexWithMax(0, 0)
            )
        )
    }

    @Test
    fun `open`() {
        val closedCell : Cell = ImmutableCell(FAKE_CELL_IDENTITY, State.CLOSE)
        closedCell.open()
    }
}