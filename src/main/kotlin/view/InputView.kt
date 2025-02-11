package view

import cell.Coordinate
import cell.CoordinateValue

object InputView {
    fun inputHeight(): Int {
        println("높이를 입력해주세요.")
        return readLineToInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력해주세요.")
        return readLineToInt()
    }

    fun inputMineCount(): Int {
        println("지뢰 개수를 입력해주세요.")
        return readLineToInt()
    }

    fun inputCoordinate(): Coordinate {
        print("open: ")
        val input =
            readlnOrNull()
                ?: throw IllegalArgumentException("좌표를 입력해주세요.")
        val (x, y) = input.split(",")

        return Coordinate(
            x = CoordinateValue(x.toInt().dec()),
            y = CoordinateValue(y.toInt().dec()),
        )
    }

    private fun readLineToInt(): Int =
        readlnOrNull()?.toInt()
            ?: throw IllegalArgumentException("숫자만 입력 가능합니다.")
}
