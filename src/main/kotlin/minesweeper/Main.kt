package minesweeper

import minesweeper.domain.MineGenerator
import minesweeper.domain.MineSweeper
import minesweeper.domain.MineSweeperMap
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMines()
    val mineSweeperMap = MineSweeperMap(height, width)
    val mines = MineGenerator.generate(mineSweeperMap, mineCount)
    val mineSweeper = MineSweeper(mineSweeperMap, mines)
    OutputView.printMineSweeperStart()
    mineSweeperMap.createPosition()
        .groupBy { it.y }
        .forEach {
            OutputView.printMineSweeper(it.value, mines)
        }
}
