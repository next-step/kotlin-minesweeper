package minesweeper.domain.strategy

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Coordinate

fun interface MinePlacementStrategy {
    fun findPlantTargetCell(cells: Map<Coordinate, Cell>): Cell
}
