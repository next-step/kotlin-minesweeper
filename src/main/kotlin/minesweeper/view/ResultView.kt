package minesweeper.view

import minesweeper.domain.Board

class ResultView {
    companion object {
        fun printBoard(board: Board) {
            for (row in board.board) {
                printRow(row)
                println()
            }
        }

        private fun printRow(row: Array<Boolean>) {
            for (cell in row) {
                print(if (cell) "X " else ". ")
            }
        }
    }
}
