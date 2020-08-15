package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) {
    fun isIt(coordinate: Coordinate): Boolean = coordinate == this

    fun isX(x: Int): Boolean = this.x == x

    fun isY(y: Int): Boolean = this.y == y
}
