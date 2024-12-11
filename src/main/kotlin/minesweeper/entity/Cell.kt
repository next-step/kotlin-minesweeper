package minesweeper.entity

sealed class Cell(val coordinate: Coordinate) {
    class Mine(coordinate: Coordinate) : Cell(coordinate)

    class Empty(coordinate: Coordinate) : Cell(coordinate)
}
