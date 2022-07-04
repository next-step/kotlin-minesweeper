package view

import domain.LandMineRandomMap.Companion.LAND_MINE_FEATURE
import domain.Line
import dto.LandMineMapUIRequest

class LandMineMapUI(private val landMineMapUIRequest: LandMineMapUIRequest) {

    fun title() = with(StringBuilder()) {
        appendLine()
        appendLine("지뢰찾기 게임 시작")
        println(this)
    }

    fun map() = with(StringBuilder()) {
        landMineMapUIRequest.mapCoordinates.map {
            appendLine(buildDisplay(it))
        }
        println(this)
    }

    private fun buildDisplay(mapLine: Line) = buildString {
        mapLine.landMineCount.map {
            append(displayLandMine(it))
        }
    }

    private fun displayLandMine(landMineCount: Int) = buildString {
        if (landMineCount == LAND_MINE_FEATURE) {
            append("* ")
        } else {
            append("$landMineCount ")
        }
    }
}
