package util

import domain.Coordinate
import domain.CoordinateGenerator

class CoordinateGeneratorFake(private val inputCoordinates: List<Coordinate>) : CoordinateGenerator {

    override fun coordinates(): List<Coordinate> {
        return inputCoordinates
    }
}
