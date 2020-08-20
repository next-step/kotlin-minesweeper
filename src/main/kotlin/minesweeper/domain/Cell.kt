package minesweeper.domain

data class Cell(val cellType: CellType, var numberOfNeighboringMine: Int = 0, var isOpen: Boolean = false) {
    fun addMine() {
        numberOfNeighboringMine++
    }

    fun open() {
        isOpen = true
    }

    fun showSymbol(): String {
        if (isOpen) {
            if (cellType != CellType.MINE && numberOfNeighboringMine > 0) {
                return "$numberOfNeighboringMine "
            }
            return cellType.symbol
        } else {
            return "\uD83C\uDF2B️️"
        }
    }
}
