package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.common.Area
import minesweeper.domain.common.PositiveInt
import minesweeper.domain.common.Rectangle

class Board private constructor(
    private val area: Area,
    val cells: Cells
) : Rectangle by area {

    companion object {
        fun of(width: PositiveInt, height: PositiveInt, cells: Cells = Cells(emptyList())): Board {
            return Board(Area(width, height), cells)
        }
    }
}
