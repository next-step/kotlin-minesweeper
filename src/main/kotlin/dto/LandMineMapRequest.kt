package dto

import domain.CoordinatePoint
import util.ConvertType

class LandMineMapRequest(
    val xCoordinatePoints: List<CoordinatePoint>,
    val yCoordinatePoints: List<CoordinatePoint>,
    val landMine: Int
) {

    companion object {
        fun of(height: String, width: String, landMine: String): LandMineMapRequest {
            val xCoordinatePoints: List<CoordinatePoint> = List(ConvertType.int(width)) { CoordinatePoint(it + 1) }
            val yCoordinatePoints: List<CoordinatePoint> = List(ConvertType.int(height)) { CoordinatePoint(it + 1) }
            return LandMineMapRequest(xCoordinatePoints, yCoordinatePoints, ConvertType.int(landMine))
        }
    }
}
