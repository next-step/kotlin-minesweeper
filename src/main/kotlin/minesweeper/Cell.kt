package minesweeper

import minesweeper.CellType.MINE
import minesweeper.CellType.NONE

class Cell(
    val row: Row,
    val column: Column,
    var cellType: CellType = NONE,
) {
    fun isMine(): Boolean = cellType == MINE
}
