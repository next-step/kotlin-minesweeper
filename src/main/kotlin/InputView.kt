object InputView {
    private const val NOT_INT_ERROR_MESSAGE = "정수 값을 입력해주세요"

    fun inputHeight(): Int {
        return inputIntValue("높이를 입력하세요.")
    }

    fun inputWidth(): Int {
        return inputIntValue("너비를 입력하세요.")
    }

    fun inputMineCount(): Int {
        return inputIntValue("지뢰는 몇 개인가요?")
    }

    private fun inputIntValue(message: String): Int {
        println(message)
        val input = inputUntilInt()
        println()
        return input
    }

    private fun inputUntilInt(): Int {
        var input = readln().toIntOrNull()

        while (input == null) {
            println(NOT_INT_ERROR_MESSAGE)
            input = readln().toIntOrNull()
        }
        return input
    }
}
