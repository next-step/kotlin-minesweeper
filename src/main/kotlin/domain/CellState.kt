package domain

class CellState private constructor(private val open: Boolean, private val mine: Boolean) {
    constructor() : this(CLOSED, NOT_MINE)

    fun isOpen(): Boolean = open
    fun isMine(): Boolean = mine
    fun open(): CellState = CellState(OPEN, mine)
    fun layMine(): CellState = CellState(open, MINE)

    companion object {
        private const val OPEN = true
        private const val CLOSED = false
        private const val MINE = true
        private const val NOT_MINE = false
    }
}
