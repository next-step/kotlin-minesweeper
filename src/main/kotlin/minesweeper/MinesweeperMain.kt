package minesweeper

import minesweeper.model.MineSweeperGame
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val row = InputView.getHeight()
    val col = InputView.getWidth()
    val mineCount = InputView.getMineCount()

    val mineSweeperGame = MineSweeperGame(row, col)
    mineSweeperGame.generateMine(mineCount)

    OutputView.printTitle()
    OutputView.printMineSweeper(mineSweeperGame.board)
}
