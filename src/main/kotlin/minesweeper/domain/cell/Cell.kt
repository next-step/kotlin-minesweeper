package minesweeper.domain.cell

import minesweeper.domain.cell.CellType.MINE
import minesweeper.domain.cell.CellType.ZERO

class Cell(
    val coordinate: Coordinate,
    private var cellType: CellType = ZERO,
    isDisplay: Boolean = false,
) {
    var isDisplay: Boolean = isDisplay
        private set

    constructor(row: Int, column: Int) : this(Coordinate(row, column))

    fun isMine(): Boolean = cellType == MINE

    fun isZero(): Boolean = cellType == ZERO

    fun openCellType(): CellType {
        if (isDisplay) {
            return cellType
        }
        return CellType.UNKNOWN
    }

    fun changeToMine() {
        cellType = MINE
    }

    fun changeToCellType(cellType: CellType) {
        this.cellType = cellType
    }

    fun changeToDisplay() {
        check(isDisplay.not()) { "이미 Display 상태입니다." }
        this.isDisplay = true
    }
}
