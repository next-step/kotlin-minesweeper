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
    val height = board.size.height
    val width = board.size.width
    repeat(mineNumber) {
        val heightGenerator: () -> Int = { Random.nextInt(height) }
        val widthGenerator: () -> Int = { Random.nextInt(width) }
        board.setMine(heightGenerator, widthGenerator)
    }
}

fun showResult(board: GameBoard) {
    OutputView.showMineSweeper(board)
}
