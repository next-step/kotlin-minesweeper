package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Location

data class Row(private val cells: List<Cell>) {
    fun cells(): List<Cell> = cells.toList()

    fun find(location: Location): Cell? = cells.firstOrNull { it.location() == location }
}
