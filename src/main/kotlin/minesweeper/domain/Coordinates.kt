package minesweeper.domain

class Coordinates(
    private val coordinates: Set<Coordinate>,
) : Set<Coordinate> by coordinates {
    fun checkWithinBounds(maxHeight: Int, maxWidth: Int): Boolean {
        return coordinates.all {
            it.checkWithinBounds(maxHeight, maxWidth)
        }
    }
}
