package minesweeper.domain

data class Cell(
    private var status: CellStatus = CellStatus.EMPTY
) {
    fun status(): CellStatus {
        return this.status
    }

    fun locateMine() {
        status = CellStatus.MINE
    }
}
