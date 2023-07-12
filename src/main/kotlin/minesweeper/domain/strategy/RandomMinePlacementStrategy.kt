package minesweeper.domain.strategy

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Coordinate

class RandomMinePlacementStrategy : MinePlacementStrategy {
    override fun findPlantTargetCell(cells: Map<Coordinate, Cell>): Cell =
        cells.filterNot { it.value.isMine() }.values.random()
}
