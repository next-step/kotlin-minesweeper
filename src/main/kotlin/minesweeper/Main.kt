package minesweeper

import minesweeper.domain.MineMap
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val height = inputView.inputMapHeight()
    val width = inputView.inputMapWidth()
    val mineCount = inputView.inputMapMineCount()

    val mineMap = MineMap(height, width, mineCount)
    mineMap.plantMine()
    resultView.outputGameStart(mineMap)
}
