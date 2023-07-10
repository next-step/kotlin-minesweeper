package minesweeper.domain.board

import minesweeper.domain.cell.Cells

interface BoardCellsCreationStrategy {
    fun create(width: Int, height: Int, countOfMine: Int): Cells
}
