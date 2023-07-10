package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.ClearCell
import minesweeper.domain.point.Point

class RandomBasedCreationStrategy : BoardCellsCreationStrategy {

    override fun create(width: Int, height: Int, countOfMine: Int): Cells {
        val points = Point.square(width, height)
        val minePoints = points.shuffled().take(countOfMine)

        val clearCells = points.map { ClearCell(it) }
        val cells = Cells.from(clearCells)

        minePoints.forEach { cells.createMine(it) }

        return cells
    }
}
