package tdd.minesweeper.domain

class GameBoard(
    height: Int,
    width: Int,
    mineCount: Int,
) {
    private val cells = Cells(height, width, mineCount)

    fun getRemainingMineCount(): Int {
        return cells.getActiveMineCount()
    }

    fun openCell(row: Int, col: Int): Boolean {
        return cells.openCellAndAdjacentCells(row - 1, col - 1)
    }

    fun getGrid(): List<List<Cell>> {
        return cells.getGrid()
    }

    fun isClear(): Boolean {
        return cells.isClear()
    }
}
