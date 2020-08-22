package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) {
    fun isX(x: Int): Boolean = this.x == x

    fun isY(y: Int): Boolean = this.y == y

    infix fun move(direction: Direction): Coordinate = Coordinate(x + direction.x, y + direction.y)
}
