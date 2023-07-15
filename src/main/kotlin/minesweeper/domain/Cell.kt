package minesweeper.domain

sealed class Cell(
    val position: Position,
) {
    var isOpened: Boolean = false
        private set

    open fun open(): Cell {
        isOpened = true
        return this
    }
}

class Mine(
    position: Position,
) : Cell(position) {
    constructor(x: Int, y: Int) : this(Position(x, y))

    override fun open(): Mine {
        super.open()
        return this
    }
}

class Normal(
    position: Position,
    val adjacentMineCount: Int = 0,
) : Cell(position) {
    constructor(x: Int, y: Int) : this(Position(x, y), 0)

    override fun open(): Normal {
        super.open()
        return this
    }

    fun isAdjacentMineCountZero(): Boolean {
        return adjacentMineCount == 0
    }
}
