package minesweeper.domain

sealed interface Cell {
    val coordinate: Coordinate
}

data class MinedCell(
    override val coordinate: Coordinate,
) : Cell {
    constructor(x: Int, y: Int) : this(Coordinate(x, y))
}

data class EmptyCell(
    override val coordinate: Coordinate,
) : Cell {
    constructor(x: Int, y: Int) : this(Coordinate(x, y))
}
