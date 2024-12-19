package minesweeper.model

class TestMinesStrategy : MinesStrategy {
    override fun addMines(
        cells: Cells,
        mineCount: Int,
    ): Cells {
        repeat(mineCount) {
            cells.cellList[it].addMine()
        }
        return cells
    }
}
