package minesweeper.ui

object InputView {
    private const val HEIGHT_PROMPT = "높이를 입력하세요."
    private const val WIDTH_PROMPT = "너비를 입력하세요."
    private const val MINES_PROMPT = "지뢰의 개수를 입력하세요."
    private const val NUMBER_ERROR_MESSAGE = "숫자를 입력해주세요."
    private const val COORDINATES_PROMPT = "open: "
    private const val COORDINATES_ERROR_MESSAGE = "잘못된 죄표값입니다. 쉼표로 구분된 두 개의 1 이상의 숫자를 입력해주세요."
    private val COORDINATES_REGEX = """^\s*([1-9]\d*)\s*,\s*([1-9]\d*)\s*$""".toRegex()
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

        // 입력값이 정규식과 매치되지 않으면 에러 메시지 출력 후 재입력
        val (first, second) =
            COORDINATES_REGEX.matchEntire(input)?.destructured ?: run {
                println(COORDINATES_ERROR_MESSAGE)
                return getCoordinates()
            }

        // 0부터 시작하는 인덱스로 변환
        return (first.toInt() - 1) to (second.toInt() - 1)
    }

    private fun getInt(): Int =
        readln().toIntOrNull() ?: run {
            println(NUMBER_ERROR_MESSAGE)
            getInt()
        }
}
