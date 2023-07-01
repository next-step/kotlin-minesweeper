package minesweeper.controller

import minesweeper.domain.MapOpenResult
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

        if (result == MapOpenResult.GAME_OVER) {
            ResultView.printGameOver()
            return false
        }
        if (result == MapOpenResult.GAME_CLEAR) {
            ResultView.printGameClear()
            return false
        }
        return true
    }
}
