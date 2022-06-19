package domain

sealed class Cell(val coordinate: Coordinate) {
    val x = coordinate.x
    val y = coordinate.y
}

class Mine(coordinate: Coordinate) : Cell(coordinate)

class Empty(coordinate: Coordinate) : Cell(coordinate)
