package minesweeper.domain

interface Cell {
    val open: Boolean
    val exploded: Boolean
    val bomb: Boolean
    val count: Int
    fun open()
    fun quietlyOpen()
    fun done(): Boolean
}

class CellWithState(private val cellState: CellState) : Cell {
    override val open: Boolean
        get() = cellState.open
    override val exploded: Boolean
        get() = this.open && cellState.bomb
    override val bomb: Boolean = cellState.bomb
    override val count: Int = cellState.count

    override fun open() {
        cellState.discover()
    }

    override fun quietlyOpen() {
        cellState.turnOpen()
    }

    override fun done(): Boolean {
        return cellState.open || cellState.bomb
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
