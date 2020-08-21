package view

import model.Line
import model.Mine
import kotlin.system.exitProcess

class InputView {
    fun inputWidth(): Line {
        println("넓이를 입력하세요.")
        val input = readLine()
        println()
        checkNotNull(input)
        try {
            return Line(input)
        } catch (ex: IllegalArgumentException) {
            exitProcess(0)
        }
    }

    fun inputHeight(): Line {
        println("높이를 입력하세요.")
        val input = readLine()
        println()
        checkNotNull(input)
        try {
            return Line(input)
        } catch (ex: IllegalArgumentException) {
            exitProcess(0)
        }
    }

    fun inputMine(): Mine {
        println("지뢰는 몇 개인가요?")
        val input = readLine()
        println()
        checkNotNull(input)
        try {
            return Mine(input)
        } catch (ex: IllegalArgumentException) {
            exitProcess(0)
        }
    }
}
