package tdd.minesweeper.domain

data class ClosedCell(
    private val hasMine: Boolean = false,
    override val adjacentMines: AdjacentMines = AdjacentMines(0),
) : Cell {
    constructor(adjacentMines: Int) : this(adjacentMines = AdjacentMines(adjacentMines))

    override fun isOpen(): Boolean = false

    override fun hasMine(): Boolean = hasMine

    override fun open(): Cell {
        if (hasMine) {
            return MineCell
        }
        return NumberCell(adjacentMines)
    }

    override fun isExpandableToAdjacent(): Boolean {
        return !hasMine() && adjacentMines == AdjacentMines(0)
    }

    fun withAdjacentMines(newAdjacentMines: AdjacentMines): ClosedCell = this.copy(adjacentMines = newAdjacentMines)
}
