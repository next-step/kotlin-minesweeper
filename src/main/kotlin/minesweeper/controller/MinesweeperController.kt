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
        ResultView.printGameStart()
        while (true) { // TODO 게임종료 조건이 있을것
            val point = InputView.getOpenPoint()
            minesweeperMap.open(point)
            ResultView.printMap(minesweeperMap)
        }
    }
}
