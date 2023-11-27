package view

import domain.field.Point
import domain.field.Point.Companion.inputListToPoint

object InputView {

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }

    fun inputOpenSpot(): Point {
        print("open: ")
        return readln()
            .split(",")
            .map { it.trim().toIntOrNull() ?: throw IllegalArgumentException("숫자만 입력 가능합니다.") }
            .inputListToPoint()
    }
}
