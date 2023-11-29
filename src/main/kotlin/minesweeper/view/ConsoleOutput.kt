package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell

object ConsoleOutput {
    fun printBoard(height: Int, width: Int, board: Board) {
        println("지뢰찾기 게임 시작")

        for (row in 0 until height) {
            for (col in 0 until width) {
                val mark = when (board.at(row, col)) {
                    Cell.MINE -> "*"
                    Cell.EMPTY -> "C"
                }
                print("$mark ")
            }
            println()
        }
    }
}
