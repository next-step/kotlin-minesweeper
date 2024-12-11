package minesweeper.entity

sealed class Cell(val coordinate: Coordinate) {
    var isRevealed: Boolean = false
        private set

    fun open() {
        if (!isRevealed) {
            isRevealed = true
        }
    }

    class Mine(coordinate: Coordinate) : Cell(coordinate)

    class Empty(coordinate: Coordinate) : Cell(coordinate)
}
