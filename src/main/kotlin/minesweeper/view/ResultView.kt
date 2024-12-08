package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Land
import minesweeper.domain.Mine

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

        private fun printCell(row: List<Cell>) {
            for (cell in row) {
                print("${if (cell is Mine) "X" else "${(cell as Land).adjacentMines}"} ")
            }
        }
    }
}
