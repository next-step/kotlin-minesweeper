package view

import model.Board

object ResultView {

    fun printBoard(board: Board) {
        println("지뢰 찾기 게임 시작")
        board.convertToMineCount().map {
            println(it.joinToString())
        }
    }

    fun printError(exception: Exception) {
        println(exception.message)
    }
}
