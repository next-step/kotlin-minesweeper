package minesweeper.controller

import minesweeper.domain.MineSweeperBoard
import minesweeper.view.InputView

class GameController {
    fun run() {
        val gameBoard = setUp()
    }

    private fun setUp(): MineSweeperBoard {
        val height = InputView.readHeight()
        val width = InputView.readWidth()
        val mineCount = InputView.readMineCount()

        return MineSweeperBoard(height, width, mineCount)
    }
}
