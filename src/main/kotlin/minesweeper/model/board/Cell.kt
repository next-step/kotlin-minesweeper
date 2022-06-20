package minesweeper.model.board

import minesweeper.model.board.coordinate.Coordinate
import minesweeper.model.board.coordinate.Position

sealed class Cell(open val position: Position) : Coordinate by position {

    data class Mine(override val position: Position) : Cell(position)

    data class Safe(override val position: Position, val surroundMineCount: Int) : Cell(position) {
        init {
            require(surroundMineCount in RANGE_OF_SURROUND_MINE_COUNT)
        }
    }

    companion object {
        private const val MIN_SURROUND_MINE_COUNT = 0
        private const val MAX_SURROUND_MINE_COUNT = 8
        private val RANGE_OF_SURROUND_MINE_COUNT = MIN_SURROUND_MINE_COUNT..MAX_SURROUND_MINE_COUNT
    }
}

class Cells(val cellList: List<Cell>) : List<Cell> by cellList {
    val mineCount: Int by lazy { this.count { it is Cell.Mine } }
    val safeCellCount: Int by lazy { this.count { it is Cell.Safe } }
}
