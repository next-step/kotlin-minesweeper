package domain

import domain.Cell.EmptyCell
import domain.Cell.MineCell

class Cells(private val cells: List<Cell> = emptyList()) {
    fun mineCells(): List<MineCell> = cells.filterIsInstance<MineCell>()

    fun emptyCells(): List<EmptyCell> = cells.filterIsInstance<EmptyCell>()
}
