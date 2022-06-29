package dto

import domain.Coordinate
import domain.CoordinatePoint
import util.ConvertType

class CoordinateGeneratorRequest(val coordinates: List<Coordinate>, val landMine: Int) {

    companion object {
        fun of(landMineMapRequest: LandMineMapRequest, landMine: String): CoordinateGeneratorRequest {
            val coordinates: List<Coordinate> = landMineMapRequest.height
                .flatMap { y -> coordinatesByHeight(y, landMineMapRequest) }
            return CoordinateGeneratorRequest(coordinates, ConvertType.int(landMine))
        }

        private fun coordinatesByHeight(y: CoordinatePoint, landMineMapRequest: LandMineMapRequest): List<Coordinate> {
            return landMineMapRequest.width.map { x -> Coordinate(x, y) }
        }
    }
}
