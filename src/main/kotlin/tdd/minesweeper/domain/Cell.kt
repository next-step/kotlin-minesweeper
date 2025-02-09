package tdd.minesweeper.domain

interface Cell {
    val adjacentMines: AdjacentMines?

    fun isOpen(): Boolean

    fun hasMine(): Boolean

    fun open(): Cell

    fun isExpandableToAdjacent(): Boolean
}
