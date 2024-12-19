package minesweeper.model

class RandomMinesStrategy : MinesStrategy {
    override fun addMines(
        cells: Cells,
        mineCount: Int,
    ): Cells {
        val shuffledCells = cells.cellList.shuffled()
        shuffledCells.subList(0, mineCount).forEach { it.addMine() }
        return Cells(cells.cellList)
    }
}
