package minesweeper.domain.cell

sealed class Cell {
    var isOpened: Boolean = false
        private set

    fun open() {
        isOpened = true
    }
}
