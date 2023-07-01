package minesweeper.domain

import minesweeper.domain.CellType.MINE
import minesweeper.domain.CellType.NONE

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cell

        if (row != other.row) return false
        if (column != other.column) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row.hashCode()
        result = 31 * result + column.hashCode()
        return result
    }

    companion object {
        fun of(row: Int, column: Int): Cell = Cell(Row(row), Column(column))
    }
}
