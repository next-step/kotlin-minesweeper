package minesweeper.view

import minesweeper.error.MineSweeperErrorMessage.HEIGHT_MUST_BE_POSITIVE
import minesweeper.error.MineSweeperErrorMessage.NUMBER_OF_MINES_MUST_BE_POSITIVE
import minesweeper.error.MineSweeperErrorMessage.WIDTH_MUST_BE_POSITIVE

object MineSweeperInputView {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        val height = readln().toInt()

        require(height > 0) { HEIGHT_MUST_BE_POSITIVE }
        return height
    }

    fun inputWidth(): Int {
        println("\n너비를 입력하세요.")
        val width = readln().toInt()

        require(width > 0) { WIDTH_MUST_BE_POSITIVE }
        return width
    }

    fun inputNumberOfMines(): Int {
        println("\n지뢰는 몇 개인가요?")
        val numberOfMines = readln().toInt()

        require(numberOfMines > 0) { NUMBER_OF_MINES_MUST_BE_POSITIVE }
        return numberOfMines
    }
}
