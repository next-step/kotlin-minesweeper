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
                printCell(cell)
            }
        }

        private fun printCell(cell: Cell) {
            print(
                when {
                    !cell.isOpened -> "C "
                    cell is Mine -> "X "
                    cell is Land -> "${cell.adjacentMines} "
                    else -> throw IllegalArgumentException("지원하지 않는 셀 타입입니다.")
                }
            )
        }
    }
}
