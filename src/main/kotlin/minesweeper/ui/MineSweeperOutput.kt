package minesweeper.ui

import minesweeper.domain.MineBoard

fun interface MineSweeperOutput {
    fun printMineBoard(mineBoard: MineBoard)
}
