package minesweeper.fixture

import minesweeper.domain.cell.ClearCell
import minesweeper.domain.cell.MineCell
import minesweeper.domain.point.Point

object CellFixture {
    fun mineCell(x: Int, y: Int): MineCell = MineCell(Point(x, y))
    fun clearCell(x: Int, y: Int): ClearCell = ClearCell(Point(x, y))
}
