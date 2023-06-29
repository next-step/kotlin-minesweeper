package minesweeper.io

import minesweeper.domain.MapElement
import minesweeper.domain.MineMapElement
import minesweeper.domain.MinesweeperMap
import minesweeper.domain.NumberMapElement

object ResultView {
    private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"
    private const val MINE_STRING = "*"

    fun printGameStart(minesweeperMap: MinesweeperMap) {
        println(GAME_START_MESSAGE)
        for (minesweeperMapRow in minesweeperMap) {
            println(minesweeperMapRow.joinToString(" ", transform = ::mapElementToString))
        }
    }

    private fun mapElementToString(element: MapElement): String {
        if (element.isCovered()) {
            return "C"
        }
        return when (element) {
            is MineMapElement -> MINE_STRING
            is NumberMapElement -> element.value.toString()
        }
    }
}
