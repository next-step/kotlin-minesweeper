package view

import exception.NotNumericException

class InputView {

    fun enterMinesweeperHeight(): Int {
        println("높이를 입력하세요.")
        val text = readln()
        validateNotString(text)
        return text.toInt()
    }

    fun enterMinesweeperWidth(): Int {
        println("너비를 입력하세요.")
        val text = readln()
        validateNotString(text)
        return text.toInt()
    }

    fun enterMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        val text = readln()
        validateNotString(text)
        return text.toInt()
    }

    private fun validateNotString(toCheck: String) {
        checkNotNull(toCheck.toIntOrNull()) { throw NotNumericException("숫자를 입력하셔야 합니다.") }
    }
}
