package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.CellType

object ConsoleOutput {
    fun printBoard(height: Int, width: Int, board: Board) {
        println("지뢰찾기 게임 시작")

        for (row in 0 until height) {
            for (col in 0 until width) {
                val currentCell = board.at(row, col)
                val mark = when (currentCell.type) {
                    CellType.MINE -> "*"
                    CellType.EMPTY -> board.countOf(row, col).toString()
                }
                print("$mark ")
            }
            println()
        }
    }
}
