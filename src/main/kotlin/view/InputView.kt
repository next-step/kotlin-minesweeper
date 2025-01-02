package view

object InputView {
    fun inputMineBoardHeight(): Int {
        println("높이를 입력하세요.")
        val input = parseToGameMetricOrThrow(readln())
        return input
    }

    fun inputMineBoardWidth(): Int {
        println("너비를 입력하세요.")
        val input = parseToGameMetricOrThrow(readln())
        return input
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        val input = parseToGameMetricOrThrow(readln())
        return input
    }

    private fun parseToGameMetricOrThrow(value: String): Int {
        val intValue = value.toIntOrNull() ?: throw IllegalArgumentException(INVALID_INPUT)
        return intValue
    }

    fun askMineCoordinate(): String {
        println("지뢰찾기 게임 시작")
        print("open: ")
        return readln().replace(" ", "")
    }

    private const val INVALID_INPUT = "숫자만 입력할 수 있습니다."
}
