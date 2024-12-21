package view

import domain.Col
import domain.Coordinate
import domain.Row

object InputView {
    fun inputRowSize(): Int {
        println("높이를 입력하세요.")
        val input = parseToGameMetricOrThrow(readln())
        return input
    }

    fun inputColumnSize(): Int {
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

    fun askMineCoordinate(): Coordinate {
        println("지뢰찾기 게임 시작")
        print("open: ")
        return parseToCoordinateOrThrow(readln())
    }

    private fun parseToCoordinateOrThrow(value: String): Coordinate {
        return value.replace(" ", "")
            .split(",")
            .also { require(it.size == 2) { INVALID_COORDINATE + value } }
            .map { it.toIntOrNull() ?: throw IllegalArgumentException(INVALID_COORDINATE) }
            .let { (row, col) -> Coordinate(Row(row), Col(col)) }
    }

    private const val INVALID_INPUT = "숫자만 입력할 수 있습니다."
    private const val INVALID_COORDINATE = "두가지 숫자를 콤마(,)로 구분해서 입력해주세요 "
}
