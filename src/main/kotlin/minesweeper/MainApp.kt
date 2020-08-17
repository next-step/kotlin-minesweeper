package minesweeper

import minesweeper.domain.MineMap
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    var mineCount = InputView.readCountOfMines(height, width)

    val mineMap = MineMap(Pair(height, width), mineCount)

    ResultView.showMineMap(mineMap)
}
