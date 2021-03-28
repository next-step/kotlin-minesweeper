package minesweeper.domain

internal abstract class Cell {
    abstract val hasMine: Boolean
    abstract val display: String
    abstract fun expose(cells: List<Cell>): Cell
}
