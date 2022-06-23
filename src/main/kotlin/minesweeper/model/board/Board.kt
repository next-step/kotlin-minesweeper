package minesweeper.model.board

import minesweeper.model.cell.Cell
import minesweeper.model.cell.CellBuilder
import minesweeper.model.cell.Cells
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

enum class BoardState {
    RUNNING,
    COMPLETED,
    MINE_EXPLODED;

    val isFinished: Boolean
        get() = this == COMPLETED || this == MINE_EXPLODED
}

open class Board(val area: Area, cellBuilder: CellBuilder? = null) : Area by area {

    private val _cells = Cells(area.mapNotNull { cellBuilder?.createCell(it) })
    open val cells: Cells
        get() = _cells

    var state = BoardState.RUNNING
        private set

    val isFinished: Boolean
        get() = this.state.isFinished

    private val isAllSafeCellOpen: Boolean
        get() = this.cells.none { it is Cell.Safe && it.isClosed }

    fun cellsAtRowOrNull(row: Int): Cells? = runCatching {
        Cells(this.cells.filter { it.row == row })
    }.getOrNull()

    open fun openCell(coordinate: Coordinate) {
        val targetCell = cells.cellAtOrNull(coordinate) ?: return
        if (targetCell.isOpen) {
            return
        }

        when (targetCell) {
            is Cell.Mine -> onMineCellOpen()
            is Cell.Safe -> openSafeCell(targetCell)
        }
    }

    private fun onMineCellOpen() {
        changeState(BoardState.MINE_EXPLODED)
    }

    private fun openSafeCell(cell: Cell.Safe) {
        openSafeCells(mutableSetOf(cell))
        if (this.isAllSafeCellOpen) {
            changeState(BoardState.COMPLETED)
        }
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

    private fun openAllCells() {
        this.cells.forEach { it.open() }
    }

    private fun changeState(boardState: BoardState) {
        this.state = boardState
        if (this.isFinished) {
            openAllCells()
        }
    }

    private fun Cell.surroundCellsToOpen(): List<Cell> =
        area.surroundCoordinatesOf(this.coordinate)
            .mapNotNull(cells::cellAtOrNull)
            .filter { it.isClosed }

    companion object {
        fun build(area: Area, isMineCell: (Coordinate) -> Boolean): Board {
            return Board(
                area = area,
                cellBuilder = CellBuilder(area, isMineCell)
            )
        }
    }
}
