package minesweeper.controller

object InputParser {
    private const val NUMBER_OF_COORDINATES = 2

    fun parseNumber(inputNumber: String): Int {
        return inputNumber.toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력해주세요.")
    }

    fun parseCoordinate(input: String): Pair<Int, Int> {
        val (x, y) = input.split(", ").let {
            require(it.size == NUMBER_OF_COORDINATES) { "좌표는 x, y 형식으로 입력해주세요." }
            it.map(::parseNumber)
        }
        return Pair(x, y)
    }
}
