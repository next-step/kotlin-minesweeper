package tdd.minesweeper.domain

object MineCell : Cell {
    override val adjacentMines: AdjacentMines? = null

    override fun isOpen(): Boolean = true

    override fun hasMine(): Boolean = true

    override fun open(): Cell = this

    override fun isExpandableToAdjacent(): Boolean = false
}
