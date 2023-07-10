package minesweeper2.domain

data class MinePosition(val positionX: Position, val positionY: Position) {
    constructor(positions: List<Position>) : this(positions[0], positions[1])
}
