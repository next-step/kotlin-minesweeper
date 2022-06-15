package view

import domain.MineField

object OutputView {
    fun printMineField(mineField: List<List<String>>) {
        println("지뢰찾기 게임 시작")
        mineField.forEach { row ->
            row.forEach { column -> print(column) }
            println()
        }
    }
}
