package domain

import dto.CoordinateGeneratorRequest

class CoordinateRandomGenerator(coordinateGeneratorRequest: CoordinateGeneratorRequest) : CoordinateGenerator {
    private val coordinates = coordinateGeneratorRequest.coordinates
    private val landMine = coordinateGeneratorRequest.landMine

    override fun coordinates(): List<Coordinate> {
        return coordinates.shuffled().subList(0, landMine)
    }
}
