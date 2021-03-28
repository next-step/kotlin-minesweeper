package domain.coordinategenerator

import domain.Coordinate
import domain.CoordinateFactory

class RandomCoordinatesGenerator(private val maxX: Int, private val maxY: Int, private val coordinateCount: Int) :
    CoordinatesGenerator {

    override fun generate(): Set<Coordinate> {
        val randomNthNumbers = (1..maxX * maxY).shuffled().take(coordinateCount)
        return randomNthNumbers.map { CoordinateFactory.create(maxX, maxY, it) }.toSet()
    }
}
