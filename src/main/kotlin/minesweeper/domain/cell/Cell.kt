package minesweeper.domain.cell

import minesweeper.domain.common.Position

sealed class Cell(private val position: Position)

data class Cells(private val cells: List<Cell>) : List<Cell> by cells
