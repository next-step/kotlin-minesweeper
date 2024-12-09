package minesweeper.entity

sealed class Cell {
    abstract val coordinate: Coordinate

    fun matches(coordinate: Coordinate): Boolean {
        return this.coordinate == coordinate
    }

    data class Mine(override val coordinate: Coordinate) : Cell()

    data class Empty(override val coordinate: Coordinate) : Cell()
}
