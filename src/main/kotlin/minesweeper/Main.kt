package minesweeper

import minesweeper.domain.MineGenerator
import minesweeper.domain.MineMap
import minesweeper.domain.MineSweeper
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMines()
    val mineMap = MineMap(height, width)
    val mines = MineGenerator.generate(mineMap, mineCount)
    val mineSweeper = MineSweeper(mineMap, mines)
    OutputView.printMineSweeperStart()
    mineMap.createPosition().forEach {
        OutputView.printMineSweeper(it, mines)
    }
}
