package minesweeper.domain

sealed class Cell(
    val position: Position,
)

class Mine(
    position: Position,
) : Cell(position) {
    constructor(x: Int, y: Int) : this(Position(x, y))
}

class Normal(
    position: Position,
    adjacentMineCount: Int = 0,
) : Cell(position) {
    constructor(x: Int, y: Int) : this(Position(x, y), 0)
    var adjacentMineCount: Int = adjacentMineCount
        private set

    fun increaseAdjacentMineCount() {
        adjacentMineCount++
    }
}
