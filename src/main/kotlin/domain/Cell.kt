package domain

sealed interface Cell {
    val coordinate: Coordinate

    data class MineCell(override val coordinate: Coordinate) : Cell

    data class EmptyCell(override val coordinate: Coordinate) : Cell
}
