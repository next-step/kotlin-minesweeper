package minesweeper.model.board

import minesweeper.model.board.coordinate.Area
import minesweeper.model.board.coordinate.Position

class Board private constructor(private val area: Area, val cells: Cells) : Area by area {

    private var _state = BoardState.MINE_EXPLODED
    val state: BoardState
        get() = this._state

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
            is Cell.Mine -> openMineCell(targetCell)
            is Cell.Safe -> openSafeCell(targetCell)
        }
    }

    private fun openMineCell(cell: Cell.Mine) {
        openAllCells()
        this._state = BoardState.MINE_EXPLODED
    }

    private fun openSafeCell(cell: Cell.Safe) {
        openSafeCell(mutableSetOf(cell))
    }

    private tailrec fun openSafeCell(cellsToOpen: MutableSet<Cell>) {

        val targetCell = cellsToOpen.firstOrNull() ?: return
        cellsToOpen.remove(targetCell)
        targetCell.open()

        if (targetCell is Cell.Safe && targetCell.isNoSurroundMine) {
            val surroundCellsToOpen = area.surroundPositionsOf(targetCell.position)
                .mapNotNull(::cellAtOrNull)
                .filter { !it.isOpen }
            cellsToOpen.addAll(surroundCellsToOpen)
        }
        openSafeCell(cellsToOpen)
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
