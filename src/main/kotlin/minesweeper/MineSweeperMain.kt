package minesweeper

import minesweeper.view.InputView
import minesweeper.view.OutputView
import kotlin.random.Random

fun main() {
    val height = InputView.askHeight()
    val width = InputView.askWidth()
    val mineCount = InputView.askMineCount()

    val mineCells = initMineCells(height, width, mineCount)
    val mineBoard = MineBoard(mineCells)
    OutputView.printMineBoard(mineBoard)
}

private fun initMineCells(height: Int, width: Int, mineCount: Int): Array<Array<MineCell>> {
    val mineCells = Array(height) { Array(width) { MineCell() } }
    repeat(mineCount) {
        val randomIndex = Random.nextInt(height * width)
        val randomHeight = randomIndex / width
        val randomWidth = randomIndex % width
        mineCells[randomHeight][randomWidth] = MineCell(true)
    }
    return mineCells
}
