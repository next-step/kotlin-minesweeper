package minesweeper

import minesweeper.domain.Height
import minesweeper.domain.MineIndexes
import minesweeper.domain.MineMap
import minesweeper.domain.MineMapSize
import minesweeper.domain.RandomIndexesGenerator
import minesweeper.domain.Width
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val height = Height(InputView.receiveHeight())
    val width = Width(InputView.receiveWidth())
    val mineMapSize = MineMapSize(width, height)
    val mineCount = InputView.receiveMineCount()
    val mineIndexes = MineIndexes(RandomIndexesGenerator.generate(mineCount, mineMapSize.size()))

    val mineMap = MineMap(mineMapSize, mineIndexes)

    ResultView.printMineGame(mineMap)
}
