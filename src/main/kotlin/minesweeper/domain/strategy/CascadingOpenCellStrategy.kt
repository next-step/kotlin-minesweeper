package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.Location
import minesweeper.domain.cell.NumberOfAdjacentMines

class CascadingOpenCellStrategy : OpenCellStrategy {
    override fun open(
        originalCells: Cells,
        targetCell: Cell,
    ): Cells {
        val candidates = findOpenCandidates(targetCell, originalCells)

        return Cells(originalCells.map { if (candidates.contains(it) && it is ClosedCell) it.open() else it })
    }

    private fun findOpenCandidates(
        targetCell: Cell,
        originalCells: Cells,
    ): List<Cell> {
        val candidates = mutableListOf<Cell>()
        val queue = ArrayDeque<Cell>()
        val visitedCells = mutableListOf<Cell>()

        visitedCells.add(targetCell)
        queue.add(targetCell)
        candidates.add(targetCell)

        while (queue.isNotEmpty()) {
            val nextCell = queue.removeFirst()
            val allAdjacentLocations =
                AdjacentLocationDirection.allAdjacentLocations(nextCell.location)
            allAdjacentLocations.forEach<Location> { adjacentLocation ->
                val adjacentCell = originalCells.find { it.location == adjacentLocation }
                adjacentCell?.let { cell ->
                    if (cell is ClosedCell && cell !in visitedCells && !cell.hasLandmine) {
                        visitedCells.add(cell)
                        candidates.add(cell)

                        if (cell.numberOfAdjacentLandmines == NumberOfAdjacentMines.ZERO) {
                            queue.add(cell)
                        }
                    }
                }
            }
        }

        return candidates.toList()
    }
}
