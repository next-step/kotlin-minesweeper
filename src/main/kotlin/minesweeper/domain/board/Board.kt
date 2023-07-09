package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.ClearCell
import minesweeper.domain.cell.MineCell
import minesweeper.domain.cell.NotOpenedCell
import minesweeper.domain.point.Point

class Board(boardCellsCreationStrategy: BoardCellsCreationStrategy) {

    val cells: Cells
    val height: Int
    val width: Int
    val countOfMine: Int

    init {
        height = boardCellsCreationStrategy.height
        width = boardCellsCreationStrategy.width
        countOfMine = boardCellsCreationStrategy.countOfMine
        cells = boardCellsCreationStrategy.create()
    }

    fun open(point: Point) {
        val openedCell = cells.open(point)

        if (openedCell is MineCell) {
            throw RuntimeException()
        }

        if (openedCell is ClearCell) {
            openClearCell(openedCell.point)
        }
    }

    private fun openClearCell(point: Point) {
        val openedCell = cells.open(point)

        if (openedCell !is ClearCell) return

        point.adjacent()
            .filter { contains(it) }
            .filter { cells.at(it) is NotOpenedCell }
            .map { cells.open(it) }
            .filterIsInstance<ClearCell>()
            .forEach { openClearCell(it.point) }
    }

    private fun contains(point: Point): Boolean = point.x < width && point.y < height
    fun isClear(): Boolean = cells.notOpenedCells().size == countOfMine
}
