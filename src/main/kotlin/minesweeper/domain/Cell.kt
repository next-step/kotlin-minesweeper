package minesweeper.domain

data class Cell(
    private var status: CellStatus = CellStatus.EMPTY
) {
}
