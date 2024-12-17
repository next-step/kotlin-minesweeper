package minesweeper.model


class Board(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int,
) {
    private var cells = Cells()

    init {
        require(height > 0 || width > 0 || mineCount > 0) { "높이, 너비, 지뢰 개수는 0보다 커야합니다." }
    }

    fun createCells(): List<Cell> {
        this.cells = cells.addAll(List(height * width) { index -> Cell(index / width, index % width) })
        cells.addMines(mineCount)
        return cells.cellList
    }

    fun addMineAroundCounts() {
        cells.addMineAroundCounts()
    }
}
