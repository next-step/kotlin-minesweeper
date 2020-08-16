package minesweeper.domain

data class Cell(val cellType: CellType, var numberOfNeighboringMine: Int = 0) {
    fun addMine() {
        numberOfNeighboringMine++
    }
}
