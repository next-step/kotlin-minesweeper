package view

import model.MineCount
import model.Number
import kotlin.system.exitProcess

class InputView {
    fun inputWidth(): Number {
        println("넓이를 입력하세요.")
        return inputNumber()
    }

    fun inputHeight(): Number {
        println("높이를 입력하세요.")
        return inputNumber()
    }

    fun inputMine(): MineCount {
        println("지뢰는 몇 개인가요?")
        return MineCount(inputNumber())
    }

    private fun inputNumber(): Number {
        val input = readLine()
        println()
        checkNotNull(input)
        try {
            return Number(input)
        } catch (ex: IllegalArgumentException) {
            exitProcess(0)
        }
    }
}
