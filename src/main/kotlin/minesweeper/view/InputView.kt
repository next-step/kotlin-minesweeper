package minesweeper.view

import minesweeper.dto.HeightResult
import minesweeper.dto.NumberOfMinesResult
import minesweeper.dto.WidthResult

object InputView {

    fun inputHeight(): HeightResult {
        println("높이를 입력하세요.")
        val height = readln()

        return HeightResult(height)
    }

    fun inputWidth(): WidthResult {
        println("너비를 입력하세요.")
        val width = readln()

        return WidthResult(width)
    }

    fun inputNumberOfMines(): NumberOfMinesResult {
        println("지뢰는 몇 개인가요?")
        val number = readln()

        return NumberOfMinesResult(number)
    }
}
