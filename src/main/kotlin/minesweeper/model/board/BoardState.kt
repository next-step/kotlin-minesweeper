package minesweeper.model.board

import minesweeper.model.cell.Cell
import minesweeper.model.cell.CellGenerator
import minesweeper.model.cell.Cells
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

sealed class BoardState(open val area: Area, open val cells: Cells) {

    data class Ready(override val area: Area, val cellGenerator: CellGenerator) :
        BoardState(area, Cells.safeCellsToFillOf(area)) {

        override fun openCell(coordinate: Coordinate): BoardState {
            val playingCells = createPlayingCells(coordinate)
            return Running(this.area, playingCells).openCell(coordinate)
        }

        private fun createPlayingCells(firstClickCell: Coordinate) = Cells(
            area.mapNotNull { position -> cellGenerator.createCell(position, firstClickCell) }
        )
    }

    data class Running(override val area: Area, override val cells: Cells) : BoardState(area, cells) {

        override fun openCell(coordinate: Coordinate): BoardState {
            val targetCell = cells.cellAtOrNull(coordinate) ?: return this
            return when (targetCell) {
                is Cell.Mine -> {
                    cells.openAll()
                    this.toFinished(isWin = false)
                }
                is Cell.Safe -> this.openSafeCell(targetCell)
            }
        }

        private fun openSafeCell(cell: Cell.Safe): BoardState {
            openSafeCells(mutableSetOf(cell))
            if (cells.isAllSafeCellOpen) {
                return this.toFinished(isWin = true)
            }
            return this
        }

        private tailrec fun openSafeCells(cellsToOpen: MutableSet<Cell>) {
            val targetCell = cellsToOpen.firstOrNull() ?: return
            cellsToOpen.remove(targetCell)
            targetCell.open()
            if (targetCell is Cell.Safe && targetCell.isNoSurroundMine) {
                val surroundCellsToOpen = targetCell.surroundCellsToOpen()
                cellsToOpen.addAll(surroundCellsToOpen)
            }
            openSafeCells(cellsToOpen)
        }

        private fun Cell.surroundCellsToOpen(): List<Cell> = area.surroundCoordinatesOf(this.coordinate)
            .mapNotNull(cells::cellAtOrNull)
            .filter { it.isClosed }
    }

    data class Finished(override val area: Area, override val cells: Cells, val isWin: Boolean) :
        BoardState(area, cells) {
        init {
            cells.openAll()
        }

        override fun openCell(coordinate: Coordinate) = this
    }

    protected fun toFinished(isWin: Boolean) = Finished(this.area, this.cells, isWin)

    abstract fun openCell(coordinate: Coordinate): BoardState
}
