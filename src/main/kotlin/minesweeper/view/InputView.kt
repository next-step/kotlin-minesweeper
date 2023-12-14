package minesweeper.view

import minesweeper.controller.InputPosition
import minesweeper.controller.InputProvider

class InputView() : InputProvider {

    override fun height(): Int {
        println(HEIGHT_REQUEST_MESSAGE)
        return readIntInput()
    }

    override fun width(): Int {
        println()
        println(WIDTH_REQUEST_MESSAGE)
        return readIntInput()
    }

    override fun mineCount(): Int {
        println()
        println(MINE_COUNT_REQUEST_MESSAGE)
        return readIntInput()
    }

    override fun openPosition(): InputPosition {
        print("open : ")
        return readInput()
            .split(", ")
            .map { it.toIntOrNull() ?: throw IllegalArgumentException("오픈할 위치는 쉼표로 구분된 정수로 구성되야 합니다") }
            .let {
                if (it.size != 2) throw IllegalArgumentException("행과 열 2개의 숫자로 오픈할 위치를 입력해주세요")
                InputPosition(it.first(), it.last())
            }
    }

    private fun readIntInput(): Int =
        readInput().toIntOrNull() ?: throw IllegalArgumentException("입력 값은 정수여야 합니다")

    private fun readInput(): String =
        readlnOrNull() ?: throw IllegalArgumentException("입력 값이 없습니다")

    companion object {
        private const val HEIGHT_REQUEST_MESSAGE = "높이를 입력하세요."
        private const val WIDTH_REQUEST_MESSAGE = "너비를 입력하세요."
        private const val MINE_COUNT_REQUEST_MESSAGE = "지뢰는 몇 개인가요?"
    }
}
