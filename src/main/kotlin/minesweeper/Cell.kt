package minesweeper

import minesweeper.CellType.MINE
import minesweeper.CellType.NONE

class Cell(
    val row: Row,
    val column: Column,
    cellType: CellType = NONE,
) {
    var cellType: CellType = cellType
        private set

    fun isMine(): Boolean = cellType == MINE

    fun changeToMine() {
        check(cellType != MINE) { "지뢰는 지뢰로 변경할 수 없습니다." }
        cellType = MINE
    }
}
