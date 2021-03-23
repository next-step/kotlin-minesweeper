package minesweeper.domain

data class Cell(val bomb: Boolean = false, val count: Int = 0) {
    var open: Boolean = false
        private set

    var exploded: Boolean = false
        private set

    fun open() {
        open = true
    }

    fun explode() {
        exploded = true
    }
}
