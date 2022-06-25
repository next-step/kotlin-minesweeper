package minesweeper.model.board

import minesweeper.model.cell.CellGenerator
import minesweeper.model.cell.Cells
import minesweeper.model.cell.RandomMineLocator
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

class Board(val area: Area, initialBoardState: BoardState) : Area by area {

    private val stateReady = initialBoardState

    val cells: Cells
        get() = this.state.cells

    var state: BoardState = stateReady
        private set

    val isFinished: Boolean
        get() = this.state is BoardState.Finished

    fun openCell(coordinate: Coordinate) {
        require(coordinate in this.area)
        this.state = this.state.openCell(coordinate)
    }

    companion object {
        private const val COUNT_OF_FORCE_SAFE_CELL = 1
        val Area.maxMineCountInRandomBoard: Int
            get() = this.cellCount - COUNT_OF_FORCE_SAFE_CELL
    }
}

fun RandomBoard(area: Area, mineCount: Int) = Board(
    area = area,
    initialBoardState = BoardState.Ready(
        area = area,
        cellGenerator = CellGenerator(area, RandomMineLocator(area, mineCount))
    )
)
