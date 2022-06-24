package minesweeper.model.coordinate

interface Coordinate {
    val row: Int
    val column: Int
    val surroundCoordinates: List<Coordinate>
}
