package minesweeper

import minesweeper.domain.Board
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val board = Board.createBoard(inputView.requestBoardSpec())
    board.exposeCells()
    outputView.render(board)
}
