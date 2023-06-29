package minesweeper.controller

import minesweeper.domain.MinesweeperMap
import minesweeper.io.InputView
import minesweeper.io.ResultView

object MinesweeperController {
    fun start() {
        val height = InputView.getHeight()
        val width = InputView.getWidth()
        val mineCount = InputView.getMineCount()
        val minesweeperMap = MinesweeperMap.of(height, width, mineCount)
        ResultView.printGameStart()
        while (nextTurn(minesweeperMap)) {
            ResultView.printMap(minesweeperMap)
        }
    }

    private fun nextTurn(minesweeperMap: MinesweeperMap): Boolean {
        val point = InputView.getOpenPoint()
        val result = minesweeperMap.open(point)

        if (result.isFailure) {
            ResultView.printGameOver()
            return false
        }
        if (result.getOrThrow() == 0) {
            ResultView.printGameClear()
            return false
        }
        return true
    }
}
