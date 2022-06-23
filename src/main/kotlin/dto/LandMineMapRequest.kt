package dto

import util.ConvertType

class LandMineMapRequest(val width: List<Int>, val height: List<Int>) {

    companion object {
        fun of(height: String, width: String): LandMineMapRequest {
            val width: List<Int> = List(ConvertType.Int(width)) { it }
            val height: List<Int> = List(ConvertType.Int(height)) { it }
            return LandMineMapRequest(width, height)
        }
    }
}
