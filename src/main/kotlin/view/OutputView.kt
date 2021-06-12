package view

import model.board.Board
import model.board.Cell
import model.board.Contents
import model.board.State

object OutputView {
    fun printStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        board.rows.forEach { row ->
            println(row.cells.joinToString(separator = " ") { convertChar(it) })
        }
    }

    private fun convertChar(cell: Cell): CharSequence =
        when {
            cell.isCovered -> "C"
            cell.isFlagged -> "F"
            cell.isMine -> "*"
            else -> "${cell.contents.mineCount}"
        }
}
