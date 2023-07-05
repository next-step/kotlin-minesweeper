package minesweeper.domain

import kotlin.random.Random

class RandomCoordinateGenerator : CoordinateGenerator {
    override fun generateCoordinate(maximumOfValue: Int): Int {
        return Random.nextInt(0, maximumOfValue)
    }
}
