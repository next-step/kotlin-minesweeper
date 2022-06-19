package minesweeper.domain

fun interface MineCoordinateStrategy {
    fun mineCoordinates(coordinates: List<Coordinate>, mineCoordinateCount: Int): List<Coordinate>
}
