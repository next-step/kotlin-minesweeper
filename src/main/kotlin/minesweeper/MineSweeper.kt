package minesweeper

import minesweeper.domain.MineCountMapFactory
import minesweeper.domain.PositionGenerator
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MineSweeper {
    fun drawMap() {
        val mineMapMeta = InputView.readMineMapMeta()
        val mineMap = MineCountMapFactory(PositionGenerator(mineMapMeta)).create()
        OutputView.printGameStartMsg()
        OutputView.printMineMap(mineMapMeta, mineMap)
    }
}

fun main() {
    MineSweeper.drawMap()
}
