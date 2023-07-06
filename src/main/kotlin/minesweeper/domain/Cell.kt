package minesweeper.domain

sealed class Cell(
    val position: Position,
)
class Mine(
    position: Position,
) : Cell(position)

class Normal(
    position: Position,
    val adjacentMineCount: Int = 0,
) : Cell(position) {
    constructor(x: Int, y: Int) : this(Position(x, y), 0)
}
