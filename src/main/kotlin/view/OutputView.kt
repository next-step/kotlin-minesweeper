package view

import domain.MineBoard

object OutputView {
    fun drawMine(board: MineBoard) {
        println("지뢰찾기 게임 시작")
        board.getGroupingMap().forEach { it ->
            it.value.forEach { print("$it ") }
            println()
        }
    }
}
