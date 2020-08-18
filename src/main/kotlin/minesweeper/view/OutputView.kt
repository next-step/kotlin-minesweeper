package minesweeper.view

import minesweeper.model.Board

object OutputView {
    fun printTitle() {
        println("지뢰찾기 게임 시작")
    }

    fun printMineSweeper(board: Board) {
        for (row in board.board) {
            println(row.joinToString { it.type.symbol })
        }
    }
}
