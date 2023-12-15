package minesweeper.domain.cell

data class Cell(
    val position: Position,
    var mark: CellMark,
) {
    var isOpened: Boolean = false
        private set

    fun open(): CellMark {
        isOpened = true
        return mark
    }
}
