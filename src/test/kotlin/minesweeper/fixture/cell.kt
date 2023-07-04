package minesweeper.fixture

import minesweeper.domain.ClearCell
import minesweeper.domain.MineCell
import minesweeper.domain.Point

object CellFixture {
    fun mineCell(x: Int, y: Int): MineCell = MineCell(Point(x, y))
    fun clearCell(x: Int, y: Int): ClearCell = ClearCell(Point(x, y))
}
