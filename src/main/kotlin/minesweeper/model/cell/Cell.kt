package minesweeper.model.cell

import minesweeper.model.coordinate.Coordinate

sealed class Cell(open val coordinate: Coordinate) : Coordinate by coordinate {

    var isOpen: Boolean = false
        private set

    val isClosed: Boolean
        get() = !this.isOpen

    fun open() {
        this.isOpen = true
    }

    data class Mine(override val coordinate: Coordinate) : Cell(coordinate)

    data class Safe(override val coordinate: Coordinate, val surroundMineCount: SurroundMineCount) : Cell(coordinate) {
        val isNoSurroundMine = surroundMineCount.equals(0)
    }
}

class Cells(private val cellList: List<Cell>) : List<Cell> by cellList {
    val mineCount: Int by lazy { this.count { it is Cell.Mine } }
    val safeCellCount: Int by lazy { this.count { it is Cell.Safe } }

    fun cellAtOrNull(coordinate: Coordinate): Cell? =
        this.find { it.row == coordinate.row && it.column == coordinate.column }

    fun openAll() {
        this.forEach { it.open() }
    }
}
