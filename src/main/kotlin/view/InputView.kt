package view

import domain.InputParser
import domain.model.Point

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

    fun inputPoint(width: Int, height: Int): Point {
        print("open: ")
        val input = InputParser.parse(readln())
            .map { it.toIntOrNull() ?: throw IllegalArgumentException(NOT_INT_ERROR_MESSAGE) }.filter { it >= 0 }
        require(input.size == 2) { "0 이상의 정수를 2개 입력해주세요" }
        require(input[0] in 0 until width) {
            "첫 번째 숫자는 0 이상 $width 미만 숫자만 가능합니다. 입력값: ${input[0]}"
        }
        require(input[1] in 0 until height) {
            "두 번째 숫자는 0 이상 $height 미만 숫자만 가능합니다. 입력값: ${input[1]}"
        }
        return Point.from(input[0], input[1])
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
