package domain

data class CellState(private val open: Boolean = false, private val mine: Boolean = false) {
    fun isOpen(): Boolean = open

    fun isMine(): Boolean = mine

    fun open(): CellState = CellState(true, mine)

    fun layMine(): CellState = CellState(open, true)
}
