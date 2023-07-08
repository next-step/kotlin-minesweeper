package minesweeper.domain

sealed class Cell(
    val position: Position,
) {
    abstract fun open()
}

class Mine(
    position: Position,
) : Cell(position) {
    constructor(x: Int, y: Int) : this(Position(x, y))

    override fun open() = Unit
}

class Normal(
    position: Position,
    val adjacentMineCount: Int = 0,
) : Cell(position) {
    constructor(x: Int, y: Int) : this(Position(x, y), 0)

    var isOpened = false
        private set

    override fun open() {
        isOpened = true
    }
}
