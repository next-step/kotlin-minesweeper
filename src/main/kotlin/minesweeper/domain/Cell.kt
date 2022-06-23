package minesweeper.domain

sealed interface Cell {
    val coordinate: Coordinate

    data class None(override val coordinate: Coordinate, val aroundMineCount: Int = 0) : Cell

    data class Mine(override val coordinate: Coordinate) : Cell
}
