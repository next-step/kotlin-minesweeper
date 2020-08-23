package minesweeper.view

import minesweeper.domain.map.CityMap

private const val ANNOUNCE_GAME_START = "지뢰찾기 게임 시작"

object ResultView {

    fun showCityMap(cityMap: CityMap) {
        println("\n$ANNOUNCE_GAME_START")

        println(cityMap)
    }
}
