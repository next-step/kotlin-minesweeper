package domain

data class CellState(
    private val isOpen: Boolean = false,
    private val hasMine: Boolean = false
) {
    fun isOpen(): Boolean = isOpen

    fun hasMine(): Boolean = hasMine

    fun open(): CellState = CellState(isOpen = true, hasMine = hasMine)

    fun layMine(): CellState = CellState(isOpen = isOpen, hasMine = true)
}
