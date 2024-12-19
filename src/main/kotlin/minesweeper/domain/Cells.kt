package minesweeper.domain

import minesweeper.domain.cell.Cell

@JvmInline
value class Cells(private val values: List<Cell>) : List<Cell> by values {
    constructor(vararg values: Cell) : this(values.toList())
}
