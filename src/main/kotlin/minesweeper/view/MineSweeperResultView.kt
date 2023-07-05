package minesweeper.view

import minesweeper.domain.Board

object MineSweeperResultView {
    fun start() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        println(board)
    }
}
