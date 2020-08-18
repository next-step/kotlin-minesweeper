package minesweeper

import minesweeper.model.MineSweeperGame
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = InputView.getHeight()
    val width = InputView.getWidth()
    val mineCount = InputView.getMineCount()
    val mineSweeperGame = MineSweeperGame(height, width, mineCount)

    OutputView.printTitle()
    OutputView.printMineSweeper(mineSweeperGame.board)
}
