package minesweeper.model

/**
 * @author 이상준
 */
interface MinesStrategy {
    fun addMines(
        cells: Cells,
        mineCount: Int,
    ): Cells
}
