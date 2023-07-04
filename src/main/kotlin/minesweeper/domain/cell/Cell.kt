package minesweeper.domain.cell

import minesweeper.domain.cell.CellType.MINE
import minesweeper.domain.cell.CellType.NONE

class Cell(
    val coordinate: Coordinate,
    cellType: CellType = NONE,
) {
    var cellType: CellType = cellType
        private set

    constructor(row: Int, column: Int, cellType: CellType) : this(Coordinate(Row(row), Column(column)), cellType)
    constructor(row: Int, column: Int) : this(Coordinate(Row(row), Column(column)))

    fun isMine(): Boolean = cellType == MINE

    fun changeToMine() {
        check(cellType != MINE) { "지뢰는 지뢰로 변경할 수 없습니다." }
        cellType = MINE
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
