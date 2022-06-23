package dto

import domain.Coordinate
import util.ConvertType

class CoordinateGeneratorRequest(val coordinates: List<Coordinate>, val landMine: Int) {

    companion object {
        fun of(width: List<Int>, height: List<Int>, landMine: String): CoordinateGeneratorRequest {
            val coordinates: List<Coordinate> = height.flatMap { y -> width.map { x -> Coordinate(x, y) } }
            return CoordinateGeneratorRequest(coordinates, ConvertType.Int(landMine))
        }
    }
}
