package minesweeper.model

class Board(
    height: Int = 0,
    width: Int = 0,
    private val mineCount: Int,
) {
    var height: Int = height
        private set
    var width: Int = width
        private set
    private var cells = Cells()

    init {
        require(height > 0 || width > 0 || mineCount > 0) { "높이, 너비, 지뢰 개수는 0보다 커야합니다." }
    }

    fun createCells(minesStrategy: MinesStrategy): Board {
        this.cells = cells.addAll(List(height * width) { index -> Cell(index / width, index % width) })
        this.cells = minesStrategy.addMines(this.cells, mineCount)
        return this
    }

    fun size(): Int {
        return height * width
    }

    fun addMineAroundCounts() {
        cells.addMineAroundCounts()
    }

    fun getCells(): Cells {
        return cells
    }

    fun getRowCells(row: Int): Cells {
        return Cells(cells.cellList.filter { it.row == row })
    }

    fun getColumnCells(column: Int): Cells {
        return Cells(cells.cellList.filter { it.column == column })
    }
}
