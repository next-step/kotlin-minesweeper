package minesweeper.model.board

import minesweeper.model.cell.Cell
import minesweeper.model.cell.CellGenerator
import minesweeper.model.cell.Cells
import minesweeper.model.cell.MineLocator
import minesweeper.model.cell.RandomMineLocator
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

sealed class BoardState {
    object Ready : BoardState()
    object Running : BoardState()
    data class Finished(val isWin: Boolean) : BoardState()
}

class Board(val area: Area, mineLocator: MineLocator) : Area by area {

    private val cellGenerator = CellGenerator(area, mineLocator)
    private val initialCells = Cells.safeCellsToFillOf(area)
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
        val targetCell = cellToOpenAt(coordinate) ?: return
        when (targetCell) {
            is Cell.Mine -> onMineCellOpen()
            is Cell.Safe -> openSafeCell(targetCell)
        }
    }

    private fun cellToOpenAt(coordinate: Coordinate): Cell? = runCatching {
        require(coordinate in this.area)
        if (this.state == BoardState.Ready) {
            createPlayingCells(coordinate)
            changeState(BoardState.Running)
        }
        cells.cellAtOrNull(coordinate)
            ?.run { if (this.isClosed) this else null }
    }.getOrNull()

    private fun createPlayingCells(firstClickCell: Coordinate) {
        this.playingCells = Cells(
            area.mapNotNull { position -> cellGenerator.createCell(position, firstClickCell) }
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
        val Area.maxMineCountInRandomBoard: Int
            get() = this.cellCount - COUNT_OF_FORCE_SAFE_CELL
    }
}

fun RandomBoard(area: Area, mineCount: Int) = Board(
    area = area,
    mineLocator = RandomMineLocator(area, mineCount)
)
