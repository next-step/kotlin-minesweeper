package minesweeper.view

import minesweeper.domain.MineSweeper

object ResultView {

    fun start(mineSweeper: MineSweeper) {
        println("지뢰 찾기 게임 시작")
        println(
            mineSweeper.board.joinToString("\n") { row ->
                row.joinToString(" ")
            }
        )
    }
}
