package domain.builder

import domain.Coordinate

class CoordinateBuilder {
    private val coordinates: MutableList<Coordinate> = ArrayList()

    fun coordinate(x: Int, y: Int) {
        coordinates.add(Coordinate(x = x, y = y))
    }

    fun build(): List<Coordinate> {
        return coordinates
    }
}
