package minesweeper.view

import minesweeper.domain.MineBoard

object OutputView {
    fun printMineBoard(mineBoard: MineBoard) {
        println("지뢰찾기 게임 시작")
        mineBoard.mineCells.forEach { row ->
            println(row.mineCells.joinToString(separator = " ") { it.state})
        }
    }
}
