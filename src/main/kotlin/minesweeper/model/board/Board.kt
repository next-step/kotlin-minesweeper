package minesweeper.model.board

import minesweeper.model.cell.Cell
import minesweeper.model.cell.CellBuilder
import minesweeper.model.cell.Cells
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

sealed class BoardState {
    object Ready : BoardState()
    object Running : BoardState()
    data class Finished(val isWin: Boolean) : BoardState()
}

class Board(val area: Area, private val cellBuilder: CellBuilder) : Area by area {

    private val initialCells = Cells.Safe(area)

    private lateinit var playingCells: Cells

    val cells: Cells
        get() = when (state) {
            BoardState.Ready -> initialCells
            else -> playingCells
        }

    var state: BoardState = BoardState.Ready
        private set

    val isFinished: Boolean
        get() = this.state is BoardState.Finished

    private val isAllSafeCellOpen: Boolean
        get() = this.cells.none { it is Cell.Safe && it.isClosed }

    fun cellsAtRowOrNull(row: Int): Cells? = runCatching {
        Cells(this.cells.filter { it.row == row })
    }.getOrNull()

    fun openCell(coordinate: Coordinate) {

        val targetCell = targetCellToOpen(coordinate) ?: return
        if (targetCell.isOpen) {
            return
        }

        when (targetCell) {
            is Cell.Mine -> onMineCellOpen()
            is Cell.Safe -> openSafeCell(targetCell)
        }
    }

    private fun targetCellToOpen(coordinate: Coordinate): Cell? {
        if (coordinate !in this.area) {
            return null
        }
        preparePayingCells(coordinate)
        return cells.cellAtOrNull(coordinate)
    }

    private fun preparePayingCells(coordinate: Coordinate) {
        if (this.state == BoardState.Ready) {
            createPlayingCells(coordinate)
            changeState(BoardState.Running)
        }
    }

    private fun createPlayingCells(firstClickCell: Coordinate) {
        this.playingCells = Cells(
            area.mapNotNull { position -> cellBuilder.createCell(position, firstClickCell) }
        )
    }

    private fun onMineCellOpen() {
        changeState(BoardState.Finished(isWin = false))
    }

    private fun openSafeCell(cell: Cell.Safe) {
        openSafeCells(mutableSetOf(cell))
        if (this.isAllSafeCellOpen) {
            changeState(BoardState.Finished(isWin = true))
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

    private fun changeState(boardState: BoardState) {
        this.state = boardState
        if (this.isFinished) {
            this.cells.openAll()
        }
    }

    private fun Cell.surroundCellsToOpen(): List<Cell> =
        area.surroundCoordinatesOf(this.coordinate)
            .mapNotNull(cells::cellAtOrNull)
            .filter { it.isClosed }

    companion object {

        private const val COUNT_OF_FORCE_SAFE_CELL = 1

        fun build(area: Area, isMineCellBlock: (Coordinate, Coordinate) -> Boolean) = Board(
            area = area,
            cellBuilder = CellBuilder(area, isMineCellBlock)
        )

        fun Area.maxMineCountInRandomBoard(): Int = this.cellCount - COUNT_OF_FORCE_SAFE_CELL
    }
}
