package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Row

object MineSweeperResultView {
    fun start() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        board.rows.forEach { row ->
            println(printRow(board, row))
        }
    }

    private fun printRow(board: Board, row: Row): String {
        return row.joinToString(" ") { cell ->
            printCell(board, cell)
        }
    }

    private fun printCell(board: Board, cell: Cell): String {
        return when (cell.hasMine()) {
            true -> "*"
            false -> "${cell.countMinesNearBy}"
        }
    }
}
