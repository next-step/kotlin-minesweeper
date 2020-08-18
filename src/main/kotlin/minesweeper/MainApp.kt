package minesweeper

import minesweeper.domain.MineMap
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readCountOfMines(height, width)

    val mineMap = MineMap(height, width, mineCount)

    ResultView.showMineMap(mineMap)
}
