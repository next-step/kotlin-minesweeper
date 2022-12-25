package minesweeper

import minesweeper.view.InputView
import minesweeper.view.OutputView
import kotlin.random.Random

fun main() {
    val height = InputView.askHeight()
    val width = InputView.askWidth()
    val mineCount = InputView.askMineCount(height * width)

    val mineCells = initMineCells(height, width, mineCount)
    val mineBoard = MineBoard(mineCells)
    OutputView.printMineBoard(mineBoard)
}

private fun initMineCells(height: Int, width: Int, mineCount: Int): Array<Array<Cell>> {
    val mineCells: Array<Array<Cell>> = Array(height) { Array(width) { CleanCell() } }
    (0 until height * width).shuffled()
        .take(mineCount)
        .forEach { randomIndex ->
            val randomHeight = randomIndex / width
            val randomWidth = randomIndex % width
            mineCells[randomHeight][randomWidth] = MineCell()
        }
    return mineCells
}
