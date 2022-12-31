package com.nextstep.minesweeper.view

import com.nextstep.minesweeper.domain.Icon

class OutputView {

    fun printGameStart(cells: Array<Array<Icon>>) {
        println("\n지뢰찾기 게임 시작")

        val stringBuilder = StringBuilder()
        (cells.indices).forEach {
            val aRow = cells[it].map { icon -> icon.symbol }.joinToString(separator = " ", postfix = "\n")
            stringBuilder.append(aRow)
        }
        print(stringBuilder.toString())
    }
}
