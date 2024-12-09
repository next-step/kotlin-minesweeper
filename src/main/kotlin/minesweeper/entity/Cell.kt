package minesweeper.entity

sealed class Cell {
    abstract val coordinate: Coordinate

    data class Mine(override val coordinate: Coordinate) : Cell()

    data class Empty(override val coordinate: Coordinate) : Cell()
}
