package minesweeper.domain

class Point(val coordinate: Coordinate, isMine: Boolean = false) : Mine(isMine) {
    fun isItCoordinate(coordinate: Coordinate): Boolean = this.coordinate == coordinate

    fun isLastX(lastX: Int): Boolean = coordinate.isX(lastX)
}
