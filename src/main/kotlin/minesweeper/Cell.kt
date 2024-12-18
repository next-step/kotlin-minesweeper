package minesweeper

class Cell(private var _type: CellType = CellType.EMPTY, val position: Position) {
    val type: CellType
        get() = _type
    val x: Int
        get() = position.x
    val isMine: Boolean
        get() = type.isMine()

    fun matchRowIndex(rowIndex: Int): Boolean {
        return position.y == rowIndex
    }

    fun changeMine() {
        if (type.isMine()) {
            return
        }

        _type = CellType.Mine
    }

    fun withMineCount(count: Int): Cell {
        return Cell(CellType.fromMineCount(count), position)
    }

    fun determineCellType(cells: Cells): CellType {
        return if (isMine) {
            CellType.Mine
        } else {
            CellType.fromMineCount(cells.neighborsMineCount(position))
        }
    }

    companion object {
        fun createMine(): Cell {
            return Cell(CellType.Mine, Position(1, 1))
        }
    }
}
