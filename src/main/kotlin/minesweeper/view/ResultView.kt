package minesweeper.view

import minesweeper.model.MineBoard

interface ResultView {
    fun printMineBoard(board: MineBoard)
}
