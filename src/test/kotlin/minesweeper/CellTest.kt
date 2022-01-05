package minesweeper

import minesweeper.domain.Cell
import minesweeper.domain.Column
import minesweeper.domain.Position
import minesweeper.domain.Row
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `Cell은 row가 0인지 알 수 있다`() {
        val cell = Cell.MineCell(Position.from(Row.from(0), Column(1)))

        val isCellRowStart = cell.isRowStartCell()

        assertThat(isCellRowStart).isTrue
    }
}
