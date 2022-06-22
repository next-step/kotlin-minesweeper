package minesweeper.model.cell

import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Position

class CellBuilder(private val area: Area, private val isMineCell: (Position) -> Boolean) {

    fun createCell(position: Position): Cell {
        if (isMineCell(position)) {
            return Cell.Mine(position)
        }

        return Cell.Safe(position, surroundMineCountOf(position))
    }

    private fun surroundMineCountOf(position: Position) = SurroundMineCount(
        area.surroundPositionsOf(position)
            .count(isMineCell)
    )
}
