package com.nextstep.minesweeper.view

import com.nextstep.minesweeper.domain.MineField
import java.lang.StringBuilder

object OutputView {
    fun printInputHeightMessage() {
        println("높이를 입력하세요.")
    }

    fun printWidthHeightMessage() {
        println("너비를 입력하세요.")
    }

    fun printMineCountMessage() {
        println("지뢰는 몇 개인가요.")
    }

    fun printMineFields(mineField: MineField) {
        val sb = StringBuilder()
        val fields = mineField.fields
        for (mineRow in fields) {
            mineRow.getRows().forEach { sb.append("${it.symbol} ") }
            sb.append("\n")
        }
        println(sb.toString())
    }
}
