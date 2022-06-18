package minesweeper.view

import minesweeper.model.MineBoard

object ConsoleResultView : ResultView {
    override fun printMineBoard(board: MineBoard) {
        println("\n지뢰찾기 게임 시작")
        println(board)
    }
}
