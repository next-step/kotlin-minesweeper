package minesweeper.view

import minesweeper.domain.BoardSpec
import minesweeper.domain.NaturalNumber
import minesweeper.domain.Position

internal class InputView {
    internal fun requestBoardSpec(): BoardSpec {
        val height = requestHeight()
        println()
        val width = requestWidth()
        println()
        return BoardSpec(width, height, requestMineCount())
    }

    internal fun requestPosition(): Position {
        print("\nopen:")
        val line = readLine()!!
        val numbers = line.split(",").map { it.trim().toInt() }
        require(numbers.size == 2) {
            "정확한 값을 입력해 주세요."
        }

        return Position(numbers[0], numbers[1])
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
