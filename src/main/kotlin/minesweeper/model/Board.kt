package minesweeper.model

/**
 * @author 이상준
 */
class Board(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int,
) {
    private var cells = Cells()

    fun createCells(): List<Cell> {
        this.cells = cells.addAll(List(height * width) { index -> Cell(index / width, index % width) })
        cells.addMines(mineCount)
        return cells.cells
    }

    fun addMineAroundCounts() {
        cells.addMineAroundCounts()
    }
}
