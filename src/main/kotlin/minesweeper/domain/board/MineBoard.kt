package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Position

data class MineBoard(
    val cells: Map<Position, Cell>,
)
