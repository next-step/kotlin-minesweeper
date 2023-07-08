package minesweeper.domain.cell

import minesweeper.domain.cell.CellType.MINE
import minesweeper.domain.cell.CellType.ZERO

class Cell(
    val coordinate: Coordinate,
    cellType: CellType = ZERO,
    isDisplay: Boolean = false,
) {
    var cellType: CellType = cellType
        get() = cellType(field)
        private set

    var isDisplay: Boolean = isDisplay
        private set

    constructor(row: Int, column: Int, cellType: CellType, isDisplay: Boolean) :
        this(Coordinate(row, column), cellType, isDisplay)

    constructor(row: Int, column: Int, cellType: CellType) : this(Coordinate(row, column), cellType)

    constructor(row: Int, column: Int, isDisplay: Boolean) : this(Coordinate(row, column), isDisplay = isDisplay)

    constructor(row: Int, column: Int) : this(Coordinate(row, column))

    fun isMine(): Boolean = cellType == MINE

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

    private fun cellType(cellType: CellType): CellType {
        if (isDisplay) {
            return cellType
        }
        return CellType.UNKNOWN
    }

    override fun toString(): String {
        return "Cell(coordinate=$coordinate, cellType=$cellType, isDisplay=$isDisplay)"
    }
}
