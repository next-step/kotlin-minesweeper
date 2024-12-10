package minesweeper.entity

sealed class Cell(val coordinate: Coordinate) {
    fun matches(coordinate: Coordinate): Boolean {
        return this.coordinate == coordinate
    }

    class Mine(coordinate: Coordinate) : Cell(coordinate)

    class Empty(coordinate: Coordinate) : Cell(coordinate)
}
