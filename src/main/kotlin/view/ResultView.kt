package view

import model.Board

object ResultView {

    fun printBoard(board: Board) {
        println("지뢰 찾기 게임 시작")
        board.grid.map { row ->
            println(row.map { it.ascii.toChar() }.joinToString())
        }
    }

    fun printError(exception: Throwable?) {
        println(exception?.message)
    }
}
