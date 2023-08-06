package view

import domain.BoardSize
import java.lang.IllegalArgumentException

object InputView {
    fun requestBoardSize(): BoardSize {
        val height = requestHeight()
        val width = requestWidth()

        return BoardSize(width, height)
    }

    private fun requestHeight(): Int {
        println("높이를 입력하세요.")
        return inputWithInt()
    }

    private fun requestWidth(): Int {
        println("\n너비를 입력하세요.")
        return inputWithInt()
    }

    private fun inputWithInt() = readln().toIntOrNull() ?: throw IllegalArgumentException("정수를 입력해주세요.")

    fun requestCountOfMine(): Int {
        println("\n지뢰는 몇 개인가요?")

        return readln().toInt()
    }
}
