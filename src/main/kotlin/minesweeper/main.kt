package minesweeper

import minesweeper.domain.board.size.MineSweeperBoardSize
import minesweeper.ui.InputView
import minesweeper.ui.OutputView

fun main() {
    OutputView.enterHeight()
    val height = InputView.inputMineSweeperGameValue()
    OutputView.enterWidth()
    val width = InputView.inputMineSweeperGameValue()
    OutputView.enterMineCount()
    val mineCount = InputView.inputMineSweeperGameValue()
    val mineSweeperBoardSize = MineSweeperBoardSize(width = width, height = height)

    val mineSweeper = MineSweeper(board = mineSweeperBoardSize, mineCount = mineCount)
    OutputView.startMineSweeper()

    val mineSweeperBoard = mineSweeper.createBoard()
    OutputView.mineSweeperInitializePrinter(mineSweeperBoard)
}
