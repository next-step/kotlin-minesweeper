package minesweeper.view

object InputView {
    fun getHeight(): Int {
        return getNumber("높이를 입력하세요.")
    }

    fun getWidth(): Int {
        return getNumber("너비를 입력하세요.")
    }

    fun getMineCount(): Int {
        return getNumber("지뢰는 몇 개인가요?")
    }

    private fun getNumber(consoleMsg: String): Int {
        println(consoleMsg)
        val input = readlnOrNull() ?: throw IllegalArgumentException("입력값은 공백이거나 빈 문자열일 수 없습니다.")
        return input.toIntOrNull() ?: throw IllegalArgumentException("숫자가 아닌 입력값은 들어올 수 없습니다.")
    }
}
