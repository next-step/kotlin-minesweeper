package minesweeper.view

import minesweeper.domain.PositiveNumber

object InputView {
    fun inputHeight(): PositiveNumber {
        println("높이를 입력하세요.")
        return PositiveNumber.of(readln())
    }

    fun inputWidth(): PositiveNumber {
        println("너비를 입력하세요.")
        return PositiveNumber.of(readln())
    }

    fun inputMines(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toIntOrNull() ?: throw IllegalArgumentException("입력값이 유효하지 않습니다")
    }
}
