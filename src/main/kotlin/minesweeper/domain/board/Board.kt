package minesweeper.domain.board

import minesweeper.domain.cell.Cell

data class Board(
    val cells: Set<Cell>
)
