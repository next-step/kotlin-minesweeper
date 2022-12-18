package minesweeper.ui

import minesweeper.domain.MineBoard

class ResultView {

    fun showBoard(board: MineBoard) {
        println()
        println("지뢰찾기 게임 시작")
        println(board)
    }
}
