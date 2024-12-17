package minesweeper.model

/**
 * @author 이상준
 */
class RandomMinesStrategy : MinesStrategy {
    override fun addMines(
        cells: Cells,
        mineCount: Int,
    ): Cells {
        require(mineCount > 0 || cells.cellList.size > mineCount)
        val shuffledCells = cells.cellList.shuffled()
        shuffledCells.subList(0, mineCount).forEach { it.addMine() }
        return Cells(cells.cellList)
    }
}
