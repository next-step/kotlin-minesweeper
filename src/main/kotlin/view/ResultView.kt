package view

import domain.Board

object ResultView {
    private const val START_GAME = "지뢰찾기 게임 시작"

    fun printBoard(board: Board) {
        println(START_GAME)
        println(board)
    }
}
