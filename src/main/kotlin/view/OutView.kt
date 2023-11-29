package view

import domain.Map

object OutView {
    private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"

    fun printMap(map: Map) {
        println(GAME_START_MESSAGE)
        println(map.view())
    }
}
