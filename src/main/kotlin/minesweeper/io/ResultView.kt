package minesweeper.io

import minesweeper.domain.MapElement
import minesweeper.domain.MineMapElement
import minesweeper.domain.MinesweeperMap
import minesweeper.domain.NumberMapElement

object ResultView {
    private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"
    private const val MINE_STRING = "*"
    private const val COVERED_STRING = "C"
    private const val GAME_OVER_MESSAGE = "Lose Game."
    private const val GAME_CLEAR_MESSAGE = "Win Game."

    fun printGameStart() {
        println(GAME_START_MESSAGE)
    }

    fun printMap(minesweeperMap: MinesweeperMap) {
        for (minesweeperMapRow in minesweeperMap) {
            println(minesweeperMapRow.joinToString(" ", transform = ::mapElementToString))
        }
        println()
    }

    private fun mapElementToString(element: MapElement): String {
        if (element.isCovered()) {
            return COVERED_STRING
        }
        return when (element) {
            is MineMapElement -> MINE_STRING
            is NumberMapElement -> element.value.toString()
        }
    }

    fun printGameOver() {
        println(GAME_OVER_MESSAGE)
    }

    fun printGameClear() {
        println(GAME_CLEAR_MESSAGE)
    }
}
