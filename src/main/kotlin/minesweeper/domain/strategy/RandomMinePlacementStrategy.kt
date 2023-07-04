package minesweeper.domain.strategy

import minesweeper.domain.cell.Cell

class RandomMinePlacementStrategy : MinePlacementStrategy {
    override fun findPlantTargetCell(cells: List<Cell>): Cell = cells.filterNot { it.isMine() }.random()
}
