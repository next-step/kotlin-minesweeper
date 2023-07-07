package minesweeper.domain

class RandomCoordinateGenerator : CoordinateGenerator {
    override fun generateCoordinate(maximumOfValue: Int): Int {
        return (0 until maximumOfValue).random()
    }
}
