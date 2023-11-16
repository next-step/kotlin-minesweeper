package minesweeper.view

import minesweeper.domain.Height
import minesweeper.domain.Width

object InputView {

    fun inputHeight(): Height {
        println(INPUT_HEIGHT)
        val value = readln()
        runCatching { value.toInt() }
            .onFailure {
                println(INPUT_NUMBER_ERROR)
                return inputHeight()
            }
        return Height(value.toInt())
    }

    fun inputWidth(): Width {
        println(INPUT_WIDTH)
        val value = readln()
        runCatching { value.toInt() }
            .onFailure {
                println(INPUT_NUMBER_ERROR)
                return inputWidth()
            }
        return Width(value.toInt())
    }

    fun inputMines(): Int {
        println(INPUT_MINES)
        val value = readln()
        runCatching { value.toInt() }
            .onFailure {
                println(INPUT_NUMBER_ERROR)
                return inputMines()
            }
        return value.toInt()
    }

    private const val INPUT_HEIGHT = "높이를 입력해주세요."
    private const val INPUT_WIDTH = "너비를 입력해주세요."
    private const val INPUT_MINES = "지뢰는 몇 개인가요?"
    private const val INPUT_NUMBER_ERROR = "숫자만 입력해주세요."
}
