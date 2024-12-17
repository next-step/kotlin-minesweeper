package minesweeper.model

/**
 * @author 이상준
 */
class TestMinesStrategy : MinesStrategy {
    override fun addMines(
        cells: Cells,
        mineCount: Int,
    ): Cells {
        require(mineCount > 0 && cells.cellList.size > mineCount)
        repeat(mineCount) {
            cells.cellList[it].addMine()
        }
        return cells
    }
}
