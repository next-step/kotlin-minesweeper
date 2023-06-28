package minesweeper.io

import minesweeper.domain.MinesweeperMap

object ResultView {
    private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"
    fun printGameStart(minesweeperMap: MinesweeperMap) {
        println(GAME_START_MESSAGE)
        for (minesweeperMapRow in minesweeperMap) {
            println(minesweeperMapRow.joinToString(" "))
        }
    }
}
