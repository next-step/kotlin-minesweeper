package minesweeper.controller

import minesweeper.domain.Dimension
import minesweeper.domain.Minesweeper
import minesweeper.domain.PositiveNumber

fun main() {

    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMinCount()
    val dimension = Dimension(PositiveNumber(width), PositiveNumber(height))
    val minesweeper = Minesweeper(dimension, PositiveNumber(mineCount))

    ResultView.writeMinesweeper(minesweeper.allBlocks())
}
