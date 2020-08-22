package minesweeper.domain

private const val LOAD_SYMBOL = "\uD83C\uDF2B️️"
data class Cell(val cellType: CellType, var numberOfNeighboringMine: Int = 0, var isOpen: Boolean = false) {
    fun addMine() {
        numberOfNeighboringMine++
    }

    fun open() {
        isOpen = true
    }

    fun showSymbol(): String {
        if (isOpen) {
            return getOpenSymbol()
        }
        return LOAD_SYMBOL
    }

    private fun getOpenSymbol(): String {
        if (cellType != CellType.MINE && numberOfNeighboringMine > 0) {
            return "$numberOfNeighboringMine "
        }
        return cellType.symbol
    }
}
