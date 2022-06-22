package minesweeper.model.board

import minesweeper.model.board.coordinate.Area
import minesweeper.model.board.coordinate.Position

class Board private constructor(private val area: Area, val cells: Cells) : Area by area {

    fun cellsAtRowOrNull(row: Int): Cells? = runCatching {
        Cells(this.cells.filter { it.row == row })
    }.getOrNull()

    companion object {

        fun build(area: Area, isMineCell: (Position) -> Boolean): Board {
            val cellBuilder = CellBuilder(area, isMineCell)
            return Board(
                area = area,
                cells = Cells(area.map(cellBuilder::createCell))
            )
        }
    }
}
