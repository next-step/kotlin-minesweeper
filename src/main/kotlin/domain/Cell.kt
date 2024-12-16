package domain

sealed interface Cell {
    data class MineCell(private val coordinate: Coordinate) : Cell

    data class EmptyCell(private val coordinate: Coordinate) : Cell
}
