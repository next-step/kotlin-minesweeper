package view


object InputView {
    private const val WRONG_INPUT_MESSAGE_FOR_INT = "숫자만 사용할 수 있습니다"

    fun getIntOrThrow(): Int {
        val input = readln()
        return input.toIntOrNull() ?: throw Exception(WRONG_INPUT_MESSAGE_FOR_INT)
    }
}
