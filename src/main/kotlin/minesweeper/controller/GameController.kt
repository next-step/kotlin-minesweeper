package minesweeper.controller

import minesweeper.domain.MineSweeperBoard
import minesweeper.view.InputView
import minesweeper.view.ResultView

class GameController {
    fun run() {
        val gameBoard = setUp()
        ResultView.renderInitialBoard(gameBoard.state)
    }

    private fun setUp(): MineSweeperBoard {
        val height = InputView.readHeight()
        val width = InputView.readWidth()
        val mineCount = InputView.readMineCount()

        return MineSweeperBoard(height, width, mineCount)
    }
}
