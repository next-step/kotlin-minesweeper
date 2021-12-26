package minesweeper.view

import minesweeper.domain.Board

object ResultView {
    private const val START_GAME = "\n지뢰찾기 게임 시작"

    fun showBoard(board: Board) {
        println(START_GAME)
        for (i in 0 until board.height) {
            val now = i * board.width
            val eachRow = board.board.subList(now, now + board.width)
            println(eachRow.joinToString(" "))
        }
    }
}
