package minesweeper

import minesweeper.domain.GameBoard
import minesweeper.view.Inputview
import minesweeper.view.OutputView

fun main() {
    run()
}

fun run() {
    val board = readySize()
    readyMine(board)
    showResult(board)
}

fun readySize(): GameBoard {
    val height = Inputview.askHeight()
    val width = Inputview.askWidth()
    return GameBoard.ready(height, width)
}

fun readyMine(board: GameBoard) {
    val mineNumber = Inputview.askMineNumber()
    board.repeatPlateMineWithoutDuplication(mineNumber)
}

fun showResult(board: GameBoard) {
    OutputView.showMineSweeper(board)
}
