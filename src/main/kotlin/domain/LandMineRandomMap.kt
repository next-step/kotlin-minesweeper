package domain

import dto.LandMineMapRequest

class LandMineRandomMap(
    private val landMineMapRequest: LandMineMapRequest,
    coordinateGenerator: CoordinateGenerator
) {
    val mapLine: List<Line> = landMineMapRequest.height.map { y -> Line(landMineMapRequest.width.map { x -> Coordinate(x, y) }) }
    val landMineCoordinates: List<Coordinate> = coordinateGenerator.coordinates()
}
