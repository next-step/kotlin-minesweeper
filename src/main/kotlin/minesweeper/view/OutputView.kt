package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Row

object OutputView {
    fun printBoard(board: Board) {
        println("지뢰찾기 게임 시작")
        board.rows.forEach { row ->
            printOneRow(row)
        }
    }

    private fun printOneRow(row: Row) {
        row.cells.forEach { cell ->
            print("${cell.state.display} ")
        }
        println()
    }
}
