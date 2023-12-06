package minesweeper.domain.cell

data class Cell(
    val position: Position,
    var mark: CellMark = CellMark.EMPTY,
) {
    val isMine: Boolean = mark == CellMark.MINE
}
