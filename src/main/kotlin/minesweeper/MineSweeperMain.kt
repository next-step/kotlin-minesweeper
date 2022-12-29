package minesweeper

import minesweeper.domain.MineBoard
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = InputView.askHeight()
    val width = InputView.askWidth()
    val mineCount = InputView.askMineCount(height * width)

    val mineBoard = MineBoard.createBoard(height, width, mineCount)
    OutputView.printMineBoard(mineBoard.snapshot())
}
