package domain

import dto.LandMineMapRequest

class LandMineRandomMap(val mapLine: List<Line>, val landMineCoordinates: List<Coordinate>) {

    constructor(
        landMineMapRequest: LandMineMapRequest,
        coordinateGenerator: CoordinateGenerator
    ) : this(
        landMineMapRequest.height.map { y -> Line(landMineMapRequest.width.map { x -> Coordinate(x, y) }) },
        coordinateGenerator.coordinates()
    )
}
