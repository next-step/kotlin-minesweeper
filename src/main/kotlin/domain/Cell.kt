package domain

sealed class Cell {
    var isOpen: Boolean = false
        private set

    fun open() {
        this.isOpen = true
    }
}
