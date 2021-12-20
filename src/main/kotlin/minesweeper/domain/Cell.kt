package minesweeper.domain

sealed class Cell {

    abstract val position: Position

    fun isRowStartCell(): Boolean = position.isRowStart()

    data class MineCell(override val position: Position) : Cell()
    data class SafetyCell(override val position: Position, val mineCount: Int) : Cell()
}
