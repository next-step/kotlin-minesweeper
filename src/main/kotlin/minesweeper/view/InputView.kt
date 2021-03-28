package minesweeper.view

import minesweeper.domain.BoardSpec
import minesweeper.domain.NaturalNumber

internal class InputView {
    internal fun requestBoardSpec(): BoardSpec {
        val height = requestHeight()
        println()
        val width = requestWidth()
        println()
        return BoardSpec(width, height, requestMineCount())
    }

    private fun requestHeight(): NaturalNumber {
        println("높이를 입력하세요.")
        return NaturalNumber(readLine()!!)
    }

    private fun requestWidth(): NaturalNumber {
        println("너비를 입력하세요.")
        return NaturalNumber(readLine()!!)
    }

    private fun requestMineCount(): NaturalNumber {
        println("지뢰는 몇 개인가요?")
        return NaturalNumber(readLine()!!)
    }
}
