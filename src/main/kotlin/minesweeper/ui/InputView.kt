package minesweeper.ui

object InputView {
    private const val HEIGHT_PROMPT = "높이를 입력하세요."
    private const val WIDTH_PROMPT = "너비를 입력하세요."
    private const val MINES_PROMPT = "지뢰의 개수를 입력하세요."
    private const val NUMBER_ERROR_MESSAGE = "숫자를 입력해주세요."
    private const val COORDINATES_PROMPT = "open: "
    private const val COORDINATES_ERROR_MESSAGE = "잘못된 죄표값입니다."
    private val COORDINATES_REGEX = """^\s*(\d+)\s*,\s*(\d+)\s*$""".toRegex()
    private val NEWLINE = System.lineSeparator()

    fun getHeight(): Int {
        println(HEIGHT_PROMPT)
        return getInt()
    }

    fun getWidth(): Int {
        println(NEWLINE + WIDTH_PROMPT)
        return getInt()
    }

    fun getMineCount(): Int {
        println(NEWLINE + MINES_PROMPT)
        return getInt()
    }

    fun getCoordinates(): Pair<Int, Int> {
        print(COORDINATES_PROMPT)
        val input = readln()

        val matchResult = COORDINATES_REGEX.matchEntire(input)

        // 입력값이 정규식과 매치되지 않으면 에러 메시지 출력 후 재입력
        if (matchResult == null) {
            println(COORDINATES_ERROR_MESSAGE)
            return getCoordinates()
        }

        val (first, second) = matchResult.destructured
        return first.toInt() to second.toInt()
    }

    private fun getInt(): Int =
        readln().toIntOrNull() ?: run {
            println(NUMBER_ERROR_MESSAGE)
            getInt()
        }
}
