package minesweeper.model

interface MinesStrategy {
    fun addMines(
        cells: Cells,
        mineCount: Int,
    ): Cells
}
