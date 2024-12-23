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
        require(height > 0 && width > 0 && mineCount > 0) { "높이, 너비, 지뢰 개수는 0보다 커야합니다." }
        require(height * width > mineCount) { "지뢰 개수는 셀의 개수보다 작아야합니다." }
    }

    fun createCells(minesStrategy: MinesStrategy): Board {
        this.cells = cells.addAll(List(height * width) { index -> Cell(index / width, index % width) })
        this.cells = cells.addMines(minesStrategy, mineCount)
        return this
    }

    fun size(): Int {
        return height * width
    }

    fun addMineAroundCounts() {
        cells.addMinesAroundCounts()
    }

    fun getCells(): Cells {
        return cells
    }

    fun getRowCells(row: Int): Cells {
        return cells.getRowCells(row)
    }

    fun openCells(row: Int, column: Int): Boolean {
        if (isMine(row, column)) {
            return false
        }

        cells.openAroundCells(row, column)
        return true
    }

    fun isFinishGame(): Boolean {
        return cells.allMatch { it.isOpen || it.isMine }
    }

    private fun isMine(row: Int, column: Int): Boolean {
        return cells.match { it.isAround(row, column) }?.isMine ?: false
    }
}
