package view

import domain.BoardHeight
import domain.BoardWidth
import domain.MineCount

object InputView {
    fun inputRowSize(): BoardHeight {
        println("높이를 입력하세요.")
        val input = parseToGameMetricOrThrow(readln())
        return BoardHeight(input)
    }

    fun inputColumnSize(): BoardWidth {
        println("너비를 입력하세요.")
        val input = parseToGameMetricOrThrow(readln())
        return BoardWidth(input)
    }

    fun inputMineCount(): MineCount {
        println("지뢰는 몇 개인가요?")
        val input = parseToGameMetricOrThrow(readln())
        return MineCount(input)
    }

    private fun parseToGameMetricOrThrow(value: String): Int {
        val intValue = value.toIntOrNull() ?: throw IllegalArgumentException(INVALID_INPUT)
        return intValue
    }

    private const val INVALID_INPUT = "숫자만 입력할 수 있습니다."
}
