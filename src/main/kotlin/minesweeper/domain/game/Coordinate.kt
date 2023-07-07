package minesweeper.domain.game

data class Coordinate(val x: Int, val y: Int)

infix fun Int.position(that: Int): Coordinate = Coordinate(this, that)
