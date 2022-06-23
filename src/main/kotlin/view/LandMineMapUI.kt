package view

import domain.LandMineRandomMap
import domain.Line

class LandMineMapUI(landMineRandomMap: LandMineRandomMap) {
    private val mapCoordinates = landMineRandomMap.mapLine.reversed()
    private val landMineCoordinates = landMineRandomMap.landMineCoordinates

    fun title() {
        println()
        println("지뢰찾기 게임 시작")
    }

    fun map() {
        mapCoordinates.map {
            println()
            display(it)
        }
    }

    private fun display(mapLine: Line) {
        mapLine.coordinates.forEach {
            if (landMineCoordinates.contains(it)) print("* ")
            else print("C ")
        }
    }
}
