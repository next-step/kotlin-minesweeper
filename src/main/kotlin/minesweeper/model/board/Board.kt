package minesweeper.model.board

import minesweeper.model.board.coordinate.Area
import minesweeper.model.board.coordinate.Position

class Board private constructor(private val area: Area, val cells: Cells) : Area by area {

    private var _state = BoardState.RUNNING
    val state: BoardState
        get() = this._state

    val isFinished: Boolean
        get() = when (this.state) {
            BoardState.COMPLETED,
            BoardState.MINE_EXPLODED -> true
            else -> false
        }

    private val isAllSafeCellOpen: Boolean
        get() = this.cells.none { !it.isOpen && it is Cell.Safe }

    fun cellsAtRowOrNull(row: Int): Cells? = runCatching {
        Cells(this.cells.filter { it.row == row })
    }.getOrNull()

    fun cellAtOrNull(position: Position): Cell? =
        this.cells.find { it.row == position.row && it.column == position.column }

    fun openCell(position: Position) {
        val targetCell = cellAtOrNull(position) ?: return
        if (targetCell.isOpen) {
            return
        }

        when (targetCell) {
            is Cell.Safe -> openSafeCell(targetCell)
            is Cell.Mine -> changeState(BoardState.MINE_EXPLODED)
        }
    }

    private fun openSafeCell(cell: Cell.Safe) {
        openSafeCells(mutableSetOf(cell))
        if (this.isAllSafeCellOpen) {
            changeState(BoardState.COMPLETED)
        }
    }

    private fun changeState(boardState: BoardState) {
        this._state = boardState
        if (this.isFinished) {
            openAllCells()
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

    companion object {

        fun build(area: Area, isMineCell: (Position) -> Boolean): Board {
            val cellBuilder = CellBuilder(area, isMineCell)
            return Board(
                area = area,
                cells = Cells(area.map(cellBuilder::createCell))
            )
        }
    }
}
