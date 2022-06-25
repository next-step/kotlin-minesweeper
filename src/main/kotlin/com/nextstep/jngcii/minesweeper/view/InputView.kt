package com.nextstep.jngcii.minesweeper.view

import com.nextstep.jngcii.minesweeper.domain.MineMapMeta

object InputView {
    fun getMineMapMeta(): MineMapMeta {
        return MineMapMeta(
            rowCount = getRowCount(),
            columnCount = getColumnCount()
        )
    }

    private fun getRowCount(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    private fun getColumnCount(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun getMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }
}
