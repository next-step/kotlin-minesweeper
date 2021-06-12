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
        if (cell.state == State.COVERED) "C"
        else if (cell.state == State.FLAGGED) "F"
        else if (cell.isMine) "*"
        else "${cell.contents.mineCount}"
}
