package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Mine
import minesweeper.domain.Row

class ResultView {
    companion object {
        fun printBoard(board: Board) {
            println("게임판")
            printRow(board)
        }

        private fun printRow(board: Board) {
            for (row in board.board) {
                printCell(row)
                println()
            }
        }

        private fun printCell(row: Row) {
            for (cell in row.getRow()) {
                print("${if (cell is Mine) "X" else "."} ")
            }
        }
    }
}
