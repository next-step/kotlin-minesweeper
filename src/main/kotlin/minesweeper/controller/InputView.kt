package minesweeper.controller

object InputView {

    fun inputHeight(): Int {
        return inputNumber("높이를 입력하세요.")
    }

    fun inputWidth(): Int {
        return inputNumber("너비를 입력하세요.")
    }

    fun inputMinCount(): Int {
        return inputNumber("지뢰는 몇 개인가요?")
    }

    private fun inputNumber(message: String): Int {
        println(message)
        val number = readln().toInt()
        println()
        return number
    }
}
