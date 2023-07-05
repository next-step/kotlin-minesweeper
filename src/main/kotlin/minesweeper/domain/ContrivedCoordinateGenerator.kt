package minesweeper.domain

class ContrivedCoordinateGenerator : CoordinateGenerator {

    override fun generateCoordinate(maximumOfValue: Int): Int {
        return maximumOfValue - 1
    }
}
