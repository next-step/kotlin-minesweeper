package tdd.minesweeper.ui

import minesweeper.domain.MineBoard

interface GameOutput {
    fun printMineBoard(mineBoard: MineBoard)
    fun printGameStart()
    fun printFinished()
}
