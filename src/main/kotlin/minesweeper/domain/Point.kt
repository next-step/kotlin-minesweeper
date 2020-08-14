package minesweeper.domain

class Point(val coordinate: Coordinate, val isMine: Boolean = false) {
    fun isIt(coordinate: Coordinate): Boolean = this.coordinate == coordinate
}
