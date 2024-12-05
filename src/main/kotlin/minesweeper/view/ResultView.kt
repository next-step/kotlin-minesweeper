package minesweeper.view

import minesweeper.domain.Board

class ResultView {
    companion object {
        fun printBoard(board: Board) {
            board.display().also(::println)
        }
    }
}
