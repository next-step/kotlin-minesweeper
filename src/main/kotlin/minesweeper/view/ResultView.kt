package minesweeper.view

import minesweeper.domain.MineMap

const val ANNOUNCE_GAME_START = "지뢰찾기 게임 시작"

object ResultView {

    fun showMineMap(mineMap: MineMap) {
        println("\n$ANNOUNCE_GAME_START")

        val map = mineMap.stateOfMap()
        map.indices.forEach { println(map[it].joinToString(" ")) }
    }
}
