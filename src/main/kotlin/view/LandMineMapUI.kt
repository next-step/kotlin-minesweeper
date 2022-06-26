package view

import domain.Line
import dto.LandMineMapUIRequest

class LandMineMapUI(private val landMineMapUIRequest: LandMineMapUIRequest) {

    fun title() {
        println()
        println("지뢰찾기 게임 시작")
    }

    fun map() {
        landMineMapUIRequest.mapCoordinates.map {
            println()
            display(it)
        }
    }

    private fun display(mapLine: Line) {
        mapLine.coordinates.forEach {
            if (landMineMapUIRequest.landMineCoordinates.contains(it)) print("* ")
            else print("C ")
        }
    }
}
