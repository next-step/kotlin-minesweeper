package minesweeper.model.board

import minesweeper.model.cell.Cell
import minesweeper.model.cell.CellGenerator
import minesweeper.model.cell.Cells
import minesweeper.model.cell.MineLocator
import minesweeper.model.cell.RandomMineLocator
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

sealed class BoardState(open val cells: Cells) {
    data class Ready(override val cells: Cells) : BoardState(cells)
    data class Running(override val cells: Cells) : BoardState(cells)
    data class Finished(override val cells: Cells, val isWin: Boolean) : BoardState(cells)

    fun toFinished(isWin: Boolean) = Finished(this.cells, isWin)
}

class Board(val area: Area, mineLocator: MineLocator) : Area by area {

    private val cellGenerator = CellGenerator(area, mineLocator)

    val cells: Cells
        get() = this.state.cells

    var state: BoardState = BoardState.Ready(Cells.safeCellsToFillOf(area))
        private set

    val isFinished: Boolean
        get() = this.state is BoardState.Finished

    private val isAllSafeCellOpen: Boolean
        get() = this.cells.none { it is Cell.Safe && it.isClosed }

    fun cellsAtRowOrNull(row: Int): Cells? = runCatching {
        Cells(this.cells.filter { it.row == row })
    }.getOrNull()

    fun openCell(coordinate: Coordinate) {
        val targetCell = cellToOpenAt(coordinate) ?: return
        when (targetCell) {
            is Cell.Mine -> onMineCellOpen()
            is Cell.Safe -> openSafeCell(targetCell)
        }
    }

    private fun cellToOpenAt(coordinate: Coordinate): Cell? = runCatching {
        require(coordinate in this.area)
        if (this.state is BoardState.Ready) {
            val playingCells = createPlayingCells(coordinate)
            changeState(BoardState.Running(playingCells))
        }
        cells.cellAtOrNull(coordinate)
            ?.run { if (this.isClosed) this else null }
    }.getOrNull()

    private fun createPlayingCells(firstClickCell: Coordinate) = Cells(
        area.mapNotNull { position -> cellGenerator.createCell(position, firstClickCell) }
    )

    private fun onMineCellOpen() {
        changeState(this.state.toFinished(isWin = false))
    }

    private fun openSafeCell(cell: Cell.Safe) {
        openSafeCells(mutableSetOf(cell))
        if (this.isAllSafeCellOpen) {
            changeState(this.state.toFinished(isWin = true))
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
        val Area.maxMineCountInRandomBoard: Int
            get() = this.cellCount - COUNT_OF_FORCE_SAFE_CELL
    }
}

fun RandomBoard(area: Area, mineCount: Int) = Board(
    area = area,
    mineLocator = RandomMineLocator(area, mineCount)
)
