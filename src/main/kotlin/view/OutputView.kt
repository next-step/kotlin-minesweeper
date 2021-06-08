package view

import model.board.Board
import model.board.Contents

object OutputView {
    fun printStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        board.rows.forEach { row ->
            println(row.cells.joinToString(separator = " ") { if (it.contents == Contents.MINE) "*" else "${it.contents.mineCount}" })
        }
    }
}
