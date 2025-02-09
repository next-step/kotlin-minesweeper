package tdd.minesweeper.domain

data class NumberCell(override val adjacentMines: AdjacentMines = AdjacentMines(0)) : Cell {
    override fun isOpen(): Boolean = true

    override fun hasMine(): Boolean = false

    override fun open(): Cell = this

    override fun isExpandableToAdjacent(): Boolean = false
}
