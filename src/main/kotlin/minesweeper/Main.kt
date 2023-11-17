package minesweeper

import minesweeper.domain.MineGenerator
import minesweeper.domain.MineSweeperMap
import minesweeper.domain.MineSweeperResult
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMines()
    val mineSweeperMap = MineSweeperMap(height, width)
    val mapPositions = mineSweeperMap.createPosition()
    val mines = MineGenerator.generate(mapPositions, mineCount)
    val mineSweeperResult = MineSweeperResult(mapPositions, mines)
    OutputView.printMineSweeperStart()
    OutputView.printMineSweeper(height.value, mineSweeperResult.resultByRow)
}
