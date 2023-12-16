package minesweeper.domain.board

import minesweeper.domain.cell.Cell

data class MineBoard(
    val cells: Set<Cell>,
)
