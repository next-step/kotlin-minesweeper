package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.CellType

object ConsoleOutput {
    fun printBoard(height: Int, width: Int, board: Board) {
        println("지뢰찾기 게임 시작")

        for (row in 0 until height) {
            for (col in 0 until width) {
                val mark = when (board.at(row, col).type) {
                    CellType.MINE -> "*"
                    CellType.EMPTY -> "C"
                }
                print("$mark ")
            }
            println()
        }
    }
}
