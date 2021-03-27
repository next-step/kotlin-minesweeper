package domain.coordinategenerator

import domain.Coordinate
import domain.CoordinateFactory

class BoardCoordinatesGenerator(val width: Int, val height: Int) : CoordinatesGenerator {

    init {
        require(width > 0 && height > 0) { "너비와 높이는 0보다 커야 합니다. width: $width, height: $height" }
    }

    override fun generate(): Set<Coordinate> {
        return (1..width * height).map { CoordinateFactory.create(width, height, it) }.toSet()
    }
}
