package minesweeper.model.map

import minesweeper.model.map.coordinate.Coordinate
import minesweeper.model.map.coordinate.Position

sealed class Cell(open val position: Position) : Coordinate by position {

    data class Mine(override val position: Position) : Cell(position)

    data class Safe(override val position: Position) : Cell(position)
}

class Cells(val cellList: List<Cell>) : List<Cell> by cellList {
    val mineCount: Int by lazy { this.count { it is Cell.Mine } }
    val safeCellCount: Int by lazy { this.count { it is Cell.Safe } }
}
