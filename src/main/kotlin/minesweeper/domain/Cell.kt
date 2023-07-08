package minesweeper.domain

sealed class Cell(
    val position: Position,
) {
    var isOpened: Boolean = false
        private set

    fun open() {
        isOpened = true
    }
}

class Mine(
    position: Position,
) : Cell(position) {
    constructor(x: Int, y: Int) : this(Position(x, y))
}

class Normal(
    position: Position,
    val adjacentMineCount: Int = 0,
) : Cell(position) {
    constructor(x: Int, y: Int) : this(Position(x, y), 0)

    fun isAdjacentMineCountZero(): Boolean {
        return adjacentMineCount == 0
    }
}
