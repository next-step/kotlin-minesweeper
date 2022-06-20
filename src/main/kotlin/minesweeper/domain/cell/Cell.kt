package minesweeper.domain.cell

import minesweeper.domain.common.Position

sealed class Cell(
    private val position: Position
) {
    val x get() = position.x.value
    val y get() = position.y.value
}

data class Cells(private val cells: List<Cell>) : List<Cell> by cells
