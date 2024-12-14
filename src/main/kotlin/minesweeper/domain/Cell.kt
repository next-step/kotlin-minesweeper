package minesweeper.domain

sealed interface Cell {
    val coordinate: Coordinate
}

data class MinedCell(
    override val coordinate: Coordinate,
) : Cell

data class EmptyCell(
    override val coordinate: Coordinate,
) : Cell
