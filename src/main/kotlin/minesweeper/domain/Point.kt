package minesweeper.domain

class Point(val coordinate: Coordinate, val isMine: Boolean = false) {
    fun isItCoordinate(coordinate: Coordinate): Boolean = this.coordinate.isIt(coordinate)

    fun isLastX(lastX: Int): Boolean = coordinate.isX(lastX)
}
