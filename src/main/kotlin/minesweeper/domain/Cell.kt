package minesweeper.domain

interface Cell {
    val open: Boolean
    val exploded: Boolean
    val bomb: Boolean
    val count: Int
    val done: Boolean
    fun open()
    fun quietlyOpen()
}

class CellWithState(private val cellState: CellState) : Cell {
    override val open: Boolean
        get() = cellState.open
    override val bomb: Boolean = cellState is CellState.BombCell
    override val exploded: Boolean
        get() = open && bomb
    override val count: Int = cellState.count
    override val done: Boolean
        get() = open || bomb

    override fun open() {
        cellState.discover()
    }

    override fun quietlyOpen() {
        cellState.turnOpen()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CellWithState

        if (cellState != other.cellState) return false

        return true
    }

    override fun hashCode(): Int {
        return cellState.hashCode()
    }
}
