package dto

import util.ConvertType
import util.PositiveInt

class LandMineMapRequest(val width: List<PositiveInt>, val height: List<PositiveInt>) {

    companion object {
        fun of(height: String, width: String): LandMineMapRequest {
            val width: List<PositiveInt> = List(ConvertType.int(width)) { PositiveInt(it + 1) }
            val height: List<PositiveInt> = List(ConvertType.int(height)) { PositiveInt(it + 1) }
            return LandMineMapRequest(width, height)
        }
    }
}
