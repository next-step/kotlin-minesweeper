package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell

class ResultView {
    companion object {
        fun printBoard(board: Board) {
            for (row in board.board) {
                printRow(row)
                println()
            }
        }

        private fun printRow(row: Array<Cell>) {
            for (cell in row) {
                print(if (cell.isMine) "X " else ". ")
            }
        }
    }
}
