package view

import kotlin.system.exitProcess

object Input {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readLineToInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readLineToInt()
    }

    fun inputMine(): Int {
        println("지뢰는 몇 개인가요?")
        return readLineToInt()
    }

    private fun readLineToInt(): Int {
        return readLine()?.toIntOrNull() ?: exitProcess(-1)
    }
}
