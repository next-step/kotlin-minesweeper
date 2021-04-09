package minesweeper.domain

data class Cell(val isMine: Boolean) {
    var isOpen = false

    fun openIfNotMine() {
        if (!isMine) {
            isOpen = true
        }
    }
}
