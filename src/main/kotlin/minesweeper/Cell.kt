package minesweeper

class Cell(private var _type: CellType = CellType.DEFAULT, val position: Position) {
    val type: CellType
        get() = _type
    val x: Int
        get() = position.x
    val isMine: Boolean
        get() = type.isMine()
    val mineCount: Int
        get() = _type.mineCount()

    fun matchRowIndex(rowIndex: Int): Boolean {
        return position.y == rowIndex
    }

    fun withMineCount(count: Int): Cell {
        return Cell(CellType.fromMineCount(count), position)
    }

    fun withMine(): Cell {
        return Cell(CellType.Mine, position)
    }

    fun determineCell(cells: Cells) {
        _type = if (isMine) {
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
