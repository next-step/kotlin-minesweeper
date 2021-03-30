package minesweeper.domain

class Cell(private val cellState: CellState) {
    val open: Boolean
        get() = cellState.open
    val bomb: Boolean = cellState is CellState.Bomb
    val exploded: Boolean
        get() = open && bomb
    val count: Int = cellState.count
    val done: Boolean
        get() = open || bomb

    fun open() {
        cellState.open()
    }

    fun quietlyOpen() {
        cellState.turnOpen()
    }
}
