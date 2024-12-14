package minesweeper.entity

sealed class Cell(val coordinate: Coordinate) {
    var isRevealed: Boolean = false
        private set

    fun open() {
        if (!isRevealed) {
            isRevealed = true
        }
    }

    abstract fun isSafe(): Boolean

    class Mine(coordinate: Coordinate) : Cell(coordinate) {
        override fun isSafe(): Boolean = false
    }

    class Empty(coordinate: Coordinate) : Cell(coordinate) {
        override fun isSafe(): Boolean = true
    }
}
