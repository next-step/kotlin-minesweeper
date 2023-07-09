package minesweeper

import minesweeper.domain.MineMap
import minesweeper.domain.MineMapConfig
import minesweeper.domain.Position
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val height = inputView.inputMapHeight()
    val width = inputView.inputMapWidth()
    val mineCount = inputView.inputMapMineCount()
    val mineMapConfig = MineMapConfig(height, width, mineCount)

    val mineMap = MineMap(mineMapConfig)
    resultView.outputGameStart()

    while (true) {
        if (mineMap.mineOpened) break
        if (mineMap.checkAllEmptyOpened()) break
        val openPosition = inputView.inputOpenPosition().let { Position(it.first, it.second) }
        mineMap.open(openPosition)
        resultView.outputMap(mineMap)
    }

    resultView.outputResult(mineMap)
}
