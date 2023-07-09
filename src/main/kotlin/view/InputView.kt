package view

object InputView {

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readNumber()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readNumber()
    }

    fun inputMine(): Int {
        println("지뢰는 몇 개인가요?")
        return readNumber()
    }

    private fun readNumber(): Int {
        val input = readLine()
        return try {
            input?.toInt() ?: throw NumberFormatException("올바른 숫자를 입력해주세요.")
        } catch (e: NumberFormatException) {
            throw e
        }
    }
}
