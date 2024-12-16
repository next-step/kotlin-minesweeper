package view

object InputView {
    fun inputRowSize(): Int {
        println("높이를 입력하세요.")
        return parseToGameMetricOrThrow(readln())
    }

    fun inputColumnSize(): Int {
        println("너비를 입력하세요.")
        return parseToGameMetricOrThrow(readln())
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return parseToGameMetricOrThrow(readln())
    }

    private fun parseToGameMetricOrThrow(value: String): Int {
        val intValue = value.toIntOrNull() ?: throw IllegalArgumentException(INVALID_INPUT)
        return intValue
    }

    private const val INVALID_INPUT = "숫자만 입력할 수 있습니다."
}
