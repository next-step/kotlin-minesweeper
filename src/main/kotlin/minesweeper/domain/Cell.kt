package minesweeper.domain

data class Cell(val cellType: CellType, var numberOfNeighboringMine: Int = 0, var isOpen: Boolean = false) {
    fun addMine() {
        numberOfNeighboringMine++
    }

    fun open() {
        isOpen = true
    }
}
