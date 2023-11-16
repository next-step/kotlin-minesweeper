package minesweeper.view

import minesweeper.domain.Mines
import minesweeper.domain.Position

object OutputView {

    fun printMineSweeperStart() {
        println(MINESWEEPER_START_MESSAGE)
    }

    fun printMineSweeper(positions: List<Position>, mines: Mines) {
        positions.forEach {
            printMine(mines.isMine(it))
        }
        println()
    }

    private fun printMine(isMine: Boolean) {
        when (isMine) {
            true -> print(MINE)
            false -> print(NO_MINE)
        }
    }

    private const val MINESWEEPER_START_MESSAGE = "지뢰찾기 게임 시작"
    private const val NO_MINE = "C "
    private const val MINE = "* "
}
