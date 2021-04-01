package minesweeper.domain

class Cell(private var cellState: CellState) {
    val bomb: Boolean = cellState is CellState.Bomb
    val open: Boolean
        get() = cellState is CellState.Open
    val exploded: Boolean
        get() = open && bomb
    val count: Int = cellState.count()
    val done: Boolean
        get() = open || bomb

    fun open() {
        if (open) {
            return
        }
        val oldState = cellState
        cellState = CellState.Open(cellState)
        oldState.openLinkedCell()
    }

    fun quietlyOpen() {
        cellState = CellState.Open(cellState)
    }
}
