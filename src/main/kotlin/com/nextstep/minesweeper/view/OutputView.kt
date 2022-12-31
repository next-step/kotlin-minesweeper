package com.nextstep.minesweeper.view

class OutputView {

    fun printGameStart(cells: Array<IntArray>) {
        println("\n지뢰찾기 게임 시작")

        val stringBuilder = StringBuilder()
        (cells.indices).forEach {
            val aRow = cells[it].joinToString(separator = " ", postfix = "\n")
            stringBuilder.append(aRow)
        }
        print(stringBuilder.toString())
    }
}
