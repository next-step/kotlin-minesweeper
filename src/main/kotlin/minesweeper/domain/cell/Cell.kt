package minesweeper.domain.cell

import minesweeper.domain.cell.CellType.MINE
import minesweeper.domain.cell.CellType.ZERO

class Cell(
    val coordinate: Coordinate,
    cellType: CellType = ZERO,
) {
    var cellType: CellType = cellType
        private set

    constructor(row: Int, column: Int, cellType: CellType) : this(Coordinate(row, column), cellType)
    constructor(row: Int, column: Int) : this(Coordinate(row, column))

    fun isMine(): Boolean = cellType == MINE

    fun changeToMine() {
        check(cellType != MINE) { "지뢰는 지뢰로 변경할 수 없습니다." }
        cellType = MINE
    }

    fun changeToCellType(cellType: CellType) {
        this.cellType = cellType
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cell

        return coordinate == other.coordinate
    }

    override fun hashCode(): Int {
        return coordinate.hashCode()
    }
}
