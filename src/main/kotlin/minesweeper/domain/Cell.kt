package minesweeper.domain

class Cell(private val cellState: CellState) {
    val open: Boolean
        get() = cellState.open
    val bomb: Boolean = cellState is CellState.BombCell
    val exploded: Boolean
        get() = open && bomb
    val count: Int = cellState.count
    val done: Boolean
        get() = open || bomb

    fun open() {
        cellState.discover()
    }

    fun quietlyOpen() {
        cellState.turnOpen()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cell

        if (cellState != other.cellState) return false

        return true
    }

    override fun hashCode(): Int {
        return cellState.hashCode()
    }
}
