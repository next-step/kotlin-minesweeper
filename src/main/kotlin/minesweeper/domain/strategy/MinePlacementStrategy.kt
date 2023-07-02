package minesweeper.domain.strategy

import minesweeper.domain.cell.Cell

fun interface MinePlacementStrategy {
    fun findPlantTargetCell(cells: List<Cell>): Cell
}
