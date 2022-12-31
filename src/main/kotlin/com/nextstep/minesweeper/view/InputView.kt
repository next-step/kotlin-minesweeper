package com.nextstep.minesweeper.view

class InputView {

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readLine()!!.toInt()
    }

    fun inputWidth(): Int {
        println("\n너비를 입력하세요.")
        return readLine()!!.toInt()
    }

    fun inputNumberOfMines(): Int {
        println("\n지뢰는 몇 개인가요?")
        return readLine()!!.toInt()
    }
}
