package minesweeper.domain.board

import minesweeper.domain.cell.Cells

interface BoardCellsCreationStrategy {
    val height: Int
    val width: Int
    val countOfMine: Int
    fun create(): Cells
}
