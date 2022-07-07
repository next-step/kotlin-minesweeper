package minesweeper.view

import minesweeper.domain.cell.Coordinate
import minesweeper.domain.cell.CoordinateValue

class InputView {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toIntOrNull() ?: throw IllegalArgumentException("높이는 숫자만 입력 가능합니다.")
    }

    fun inputWidth(): Int {
        println()
        println("너비를 입력하세요.")
        return readln().toIntOrNull() ?: throw IllegalArgumentException("너비는 숫자만 입력 가능합니다.")
    }

    fun inputMineCount(): Int {
        println()
        println("지뢰는 몇 개인가요?")
        return readln().toIntOrNull() ?: throw IllegalArgumentException("지뢰개수는 숫자만 입력 가능합니다.")
    }

    fun inputCoordinate(): Coordinate {
        print("open: ")
        val coordinateValues = readln().split(", ").map { CoordinateValue(it.toInt()) }
        require(coordinateValues.size == 2) { "x, y 좌표값을 순서대로 ', '로 나누어  입력해주세요." }
        return Coordinate(coordinateValues[0], coordinateValues[1])
    }
}
