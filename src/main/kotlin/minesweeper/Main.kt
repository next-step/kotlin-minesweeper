package minesweeper

import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.MineMap
import minesweeper.domain.MineMapSize
import minesweeper.domain.Width
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val height = Height(InputView.receiveHeight())
    val width = Width(InputView.receiveWidth())
    val mineMapSize = MineMapSize(width, height)
    val mineCount = MineCount(InputView.receiveMineCount())

    val mineMap = MineMap(mineMapSize, mineCount)

    ResultView.printMineGame(mineMap)
}
