package minesweeper.view.output

import minesweeper.model.board.Board

interface OutputView {
    fun printInitialMessage() {}
    fun printBoard(board: Board)
    fun printFinalMessage(board: Board) {}
}
