package minesweeper

import minesweeper.ui.InputView
import minesweeper.ui.OutputView

fun main() {
    OutputView.enterHeight()
    val height = InputView.inputMineSweeperGameValue()
    OutputView.enterWidth()
    val width = InputView.inputMineSweeperGameValue()
    OutputView.enterMineCount()
    val mineCount = InputView.inputMineSweeperGameValue()
    val mineSweeper = MineSweeper(width = width, height = height, mineCount = mineCount)
    OutputView.startMineSweeper()

    val mineSweeperInitializer = mineSweeper.initialize()
    OutputView.mineSweeperInitializePrinter(mineSweeperInitializer)
}
