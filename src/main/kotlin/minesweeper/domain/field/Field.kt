package minesweeper.domain.field

import minesweeper.domain.cell.Cell

data class Field(
    val cells: Set<Cell>
)
