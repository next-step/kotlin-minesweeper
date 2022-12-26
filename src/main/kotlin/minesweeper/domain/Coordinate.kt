package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) {
    init {
        require(x >= MIN_VALUE && y >= MIN_VALUE) { "x / y should greater or equal than $MIN_VALUE" }
    }

    companion object {
        private const val MIN_VALUE = 0
    }
}
