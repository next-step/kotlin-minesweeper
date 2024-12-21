package view

object InputView {
    fun inputHeight(): Int {
        println("높이를 입력해주세요.")
        return readLineToInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력해주세요.")
        return readLineToInt()
    }

    fun inputMineCount(): Int {
        println("지뢰 개수를 입력해주세요.")
        return readLineToInt()
    }

    private fun readLineToInt(): Int =
        readlnOrNull()?.toInt()
            ?: throw IllegalArgumentException("숫자만 입력 가능합니다.")
}
