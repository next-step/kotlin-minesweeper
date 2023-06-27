package minesweeper.io

import minesweeper.domain.MapElement
import minesweeper.domain.MinesweeperMap

object ResultView {
    private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"
    fun printGameStart(minesweeperMap: MinesweeperMap) {
        println(GAME_START_MESSAGE)
        for (minesweeperMapRow in minesweeperMap) {
            println(minesweeperMapRow.joinToString(" ", transform = ::mapElementToString))
        }
    }

    private fun mapElementToString(element: MapElement): String {
        return when (element) {
            MapElement.MINE -> "*"
            else -> element.ordinal.toString()
        }
    }
}
