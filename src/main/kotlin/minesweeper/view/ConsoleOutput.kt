package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell

object ConsoleOutput {
    fun printBoard(board: Board) {
        println("지뢰찾기 게임 시작")

        board.rows.rows.forEach { row ->
            row.cells.forEach { cell ->
                val mark = when (cell) {
                    Cell.MINE -> "*"
                    Cell.EMPTY -> "C"
                }
                print("$mark ")
            }
            println()
        }
    }
}
