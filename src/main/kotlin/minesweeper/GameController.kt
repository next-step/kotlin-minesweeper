package minesweeper

import minesweeper.domain.GameBoard
import minesweeper.domain.GameBoardSize
import minesweeper.view.Inputview
import minesweeper.view.OutputView
import kotlin.random.Random

fun main() {
    run()
}

fun run() {
    val size = readySize()
    val board = readyGameBoard(size)
    readyMine(board)
    showResult(board)
}

fun readySize(): GameBoardSize {
    val height = Inputview.askHeight()
    val width = Inputview.askWidth()
    return GameBoardSize(height, width)
}

fun readyGameBoard(size: GameBoardSize): GameBoard {
    return GameBoard(size)
}

fun readyMine(board: GameBoard) {
    val mineNumber = Inputview.askMineNumber()
    repeat(mineNumber) {
        randomGenerator(board)
    }
}

private fun randomGenerator(board: GameBoard) {
    do {
        val heightBoundary = board.size.height
        val widthBoundary = board.size.width

        val heightGenerator: () -> Int = { Random.nextInt(heightBoundary) }
        val widthGenerator: () -> Int = { Random.nextInt(widthBoundary) }

        val result = board.setMine(heightGenerator, widthGenerator)
    } while (!result)
}

fun showResult(board: GameBoard) {
    OutputView.showMineSweeper(board)
}
