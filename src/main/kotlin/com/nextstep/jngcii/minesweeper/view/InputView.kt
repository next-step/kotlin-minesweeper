package com.nextstep.jngcii.minesweeper.view

import com.nextstep.jngcii.minesweeper.domain.StringUtility

object InputView {
    fun getRowCount(): Int {
        println("높이를 입력하세요.")
        return inputLength()
    }

    fun getColumnCount(): Int {
        println("너비를 입력하세요.")
        return inputLength()
    }

    private fun inputLength() = StringUtility.convertToPositiveInt(readln())
}
