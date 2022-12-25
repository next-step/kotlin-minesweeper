package minesweeper

import minesweeper.view.InputView
import minesweeper.view.OutputView
import kotlin.random.Random

fun main() {
    val height = InputView.askHeight()
    val width = InputView.askWidth()
    val mineCount = InputView.askMineCount(height * width)

    val mineBoard = MineBoard.createBoard(height, width, mineCount)
    OutputView.printMineBoard(mineBoard)
}
