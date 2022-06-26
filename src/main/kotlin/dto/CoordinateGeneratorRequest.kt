package dto

import domain.Coordinate
import util.ConvertType

class CoordinateGeneratorRequest(val coordinates: List<Coordinate>, val landMine: Int) {

    companion object {
        fun of(landMineMapRequest: LandMineMapRequest, landMine: String): CoordinateGeneratorRequest {
            val coordinates: List<Coordinate> = landMineMapRequest.height.flatMap { y -> landMineMapRequest.width.map { x -> Coordinate(x, y) } }
            return CoordinateGeneratorRequest(coordinates, ConvertType.int(landMine))
        }
    }
}
