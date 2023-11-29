package minesweeper.domain.cell

data class Cell(
    val row: Int,
    val column: Int,
    val mark: CellMark = CellMark.EMPTY
)
