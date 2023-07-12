package view

import domain.MineBoard

object OutputView {
    fun printStartGame() {
        println()
        println("지뢰찾기 게임 시작")
    }

    fun printMineBoard(mineBoard: MineBoard) {
        mineBoard.info.values.forEach { cell ->
            println(cell.map { it.value }.joinToString(" "))
        }
    }
}
