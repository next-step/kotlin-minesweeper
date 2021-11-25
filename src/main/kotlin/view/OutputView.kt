package view

import dto.BoardDto

object OutputView {
    fun printStart() = println("\n지뢰찾기 게임 시작")

    fun printResult(win: Boolean) = println("\n${if (win) "Win Game." else "Lose Game."}")

    fun printBoard(board: BoardDto) = println(board)
}
