package domain

data class CellState(
    private val isOpen: Boolean = false,
    private val isMine: Boolean = false
) {
    fun isOpen(): Boolean = isOpen

    fun isMine(): Boolean = isMine

    fun open(): CellState = CellState(isOpen = true, isMine = isMine)

    fun layMine(): CellState = CellState(isOpen = isOpen, isMine = true)
}
