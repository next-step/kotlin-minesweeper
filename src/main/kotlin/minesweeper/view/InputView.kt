package minesweeper.view

import minesweeper.domain.PositiveNumber

object InputView {
    fun getHeight(): PositiveNumber {
        println("높이를 입력하세요")
        val height = readln()
        return PositiveNumber.from(height)
    }

    fun getWidth(): PositiveNumber {
        println("너비를 입력하세요")
        val width = readln()
        return PositiveNumber.from(width)
    }

    fun getMineCount(): PositiveNumber {
        println("지뢰는 몇 개인가요?")
        val count = readln()
        return PositiveNumber.from(count)
    }
}
