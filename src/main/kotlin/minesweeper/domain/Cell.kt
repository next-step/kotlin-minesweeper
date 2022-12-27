package minesweeper.domain

data class Cell(
    private var status: CellStatus = CellStatus.EMPTY
) {
    fun locateMine() {
        status = CellStatus.MINE
    }
}
