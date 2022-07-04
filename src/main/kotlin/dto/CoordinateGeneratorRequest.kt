package dto

import domain.Coordinate
import domain.CoordinatePoint
import domain.CoordinateRandomGenerator

class CoordinateGeneratorRequest {

    companion object {
        fun of(landMineMapRequest: LandMineMapRequest): CoordinateRandomGenerator {
            val coordinates: List<Coordinate> = landMineMapRequest.yCoordinatePoints
                .flatMap { y -> coordinatesByHeight(y, landMineMapRequest) }
            return toGenerator(coordinates, landMineMapRequest.landMine)
        }

        private fun toGenerator(coordinates: List<Coordinate>, landMine: Int): CoordinateRandomGenerator {
            return CoordinateRandomGenerator(coordinates, landMine)
        }

        private fun coordinatesByHeight(y: CoordinatePoint, landMineMapRequest: LandMineMapRequest): List<Coordinate> {
            return landMineMapRequest.xCoordinatePoints.map { x -> Coordinate(x, y) }
        }
    }
}
