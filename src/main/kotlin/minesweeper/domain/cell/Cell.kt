package minesweeper.domain.cell

data class Cell(
    val position: Position,
) {
    val mark: CellMark = CellMark.EMPTY
}
