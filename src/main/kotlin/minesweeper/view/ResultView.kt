package minesweeper.view

import minesweeper.domain.MineMap

private const val ANNOUNCE_GAME_START = "지뢰찾기 게임 시작"
private const val BLANK_SEPARATOR = " "

object ResultView {

    fun showMineMap(mineMap: MineMap) {
        println("\n$ANNOUNCE_GAME_START")

        val map = mineMap.getMineCountedMap()
        map.indices.forEach {
            println(map[it].joinToString(BLANK_SEPARATOR))
        }
    }
}
