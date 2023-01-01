package minesweeper

import minesweeper.view.InputView.inputHeight
import minesweeper.view.InputView.inputMineCount
import minesweeper.view.InputView.inputWidth
import minesweeper.view.OutputView.printHeight
import minesweeper.view.OutputView.printMiceCount
import minesweeper.view.OutputView.printWidth
import minesweeper.view.OutputView.startGame

fun main() {

    val height = inputHeight()
    printHeight(height)

    val width = inputWidth()
    printWidth(width)

    val mineCount = inputMineCount()
    printMiceCount(mineCount)

    startGame(height, width, mineCount)
}
