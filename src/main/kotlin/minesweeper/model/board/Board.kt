package minesweeper.model.board

import minesweeper.model.cell.Cell
import minesweeper.model.cell.CellBuilder
import minesweeper.model.cell.Cells
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Position

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

    private var _state = BoardState.RUNNING
    val state: BoardState
        get() = this._state

    val isFinished: Boolean
        get() = this.state.isFinished

    private val isAllSafeCellOpen: Boolean
        get() = this.cells.none { it is Cell.Safe && !it.isOpen }

    fun cellsAtRowOrNull(row: Int): Cells? = runCatching {
        Cells(this.cells.filter { it.row == row })
    }.getOrNull()

    fun cellAtOrNull(position: Position): Cell? =
        this.cells.find { it.row == position.row && it.column == position.column }

    open fun openCell(position: Position) {
        val targetCell = cellAtOrNull(position) ?: return
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
            val surroundCellsToOpen = area.surroundPositionsOf(targetCell.position)
                .mapNotNull(::cellAtOrNull)
                .filter { !it.isOpen }
            cellsToOpen.addAll(surroundCellsToOpen)
        }
        openSafeCells(cellsToOpen)
    }

    private fun openAllCells() {
        this.cells.forEach { it.open() }
    }

    private fun changeState(boardState: BoardState) {
        this._state = boardState
        if (this.isFinished) {
            openAllCells()
        }
    }

    companion object {
        fun build(area: Area, isMineCell: (Position) -> Boolean): Board {
            return Board(
                area = area,
                cellBuilder = CellBuilder(area, isMineCell)
            )
        }
    }
}
