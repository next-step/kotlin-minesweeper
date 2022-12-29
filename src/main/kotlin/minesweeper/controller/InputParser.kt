package minesweeper.controller

object InputParser {
    private const val MINIMUM_INPUT_POSITION = 1
    private const val MAXIMUM_OUTPUT_POSITION = 20
    private const val CORRECTION_VALUE = 1

    fun parseNumber(inputNumber: String): Int {
        return inputNumber.toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력해주세요.")
    }

    fun parsePosition(inputNumber: String): Int {
        inputNumber.toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력해주세요.")
        require(inputNumber.toInt() in MINIMUM_INPUT_POSITION..MAXIMUM_OUTPUT_POSITION) { "1~20 사이의 숫자를 입력해주세요." }
        return inputNumber.toInt() - CORRECTION_VALUE
    }
}
