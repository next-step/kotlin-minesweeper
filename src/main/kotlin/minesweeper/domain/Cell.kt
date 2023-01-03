package minesweeper.domain

sealed class Cell(
    open val position: Position,
    open val state: CellState,
) {
    abstract fun openResult(): CellOpenResult

    abstract fun copyWithOpen(): Cell

    companion object {
        const val CLOSED_SIGN = "C"
    }
}

enum class CellState {
    OPENED, CLOSED
}
