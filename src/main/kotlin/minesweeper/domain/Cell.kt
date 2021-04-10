package minesweeper.domain

data class Cell(val isMine: Boolean) {
    var isOpen = false
        private set

    fun openIfNotMine() {
        if (!isMine) {
            isOpen = true
        }
    }
}
