package minesweeper.controller

import minesweeper.domain.MinesweeperMap
import minesweeper.io.InputView
import minesweeper.io.ResultView

class MinesweeperController {
    fun start() {
        val height = InputView.getHeight()
        val width = InputView.getWidth()
        val mineCount = InputView.getMineCount()
        val minesweeperMap = MinesweeperMap.of(height, width, mineCount)
        ResultView.printGameStart(minesweeperMap)
    }
}
