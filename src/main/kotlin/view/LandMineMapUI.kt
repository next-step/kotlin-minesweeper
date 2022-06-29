package view

import domain.Coordinate
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
        mapLine.coordinates.map {
            append(displayLandMine(it))
        }
    }

    private fun displayLandMine(coordinate: Coordinate) = buildString {
        if (landMineMapUIRequest.landMineCoordinates.contains(coordinate)) {
            append("* ")
        } else {
            append("C ")
        }
    }
}
