package view

import domain.Cell.Cell

object OutputView {
    fun printMineField(mineField: List<List<Cell>>) {
        println("지뢰찾기 게임 시작")
        mineField.forEach { row ->
            row.forEach { column -> print(column.symbol) }
            println()
        }
    }
}
