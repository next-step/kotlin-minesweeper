package minesweeper.domain

sealed interface Cell {
    val coordinate: Coordinate
}

data class MinedCell(
    override val coordinate: Coordinate,
) : Cell {
    constructor(y: Int, x: Int) : this(Coordinate(y, x))
}

data class EmptyCell(
    override val coordinate: Coordinate,
) : Cell {
    constructor(y: Int, x: Int) : this(Coordinate(y, x))
}
