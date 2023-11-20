package minesweeper.view

import minesweeper.domain.Height
import minesweeper.domain.MineSweeperIndex
import minesweeper.domain.Position
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

    fun inputOpenPosition(): MineSweeperIndex {
        print(INPUT_OPEN)
        return readln().split(DELIMITER)
            .let { MineSweeperIndex(Position(it[0].trim().toInt(), it[1].trim().toInt())) }
    }

    private const val INPUT_HEIGHT = "높이를 입력해주세요."
    private const val INPUT_WIDTH = "너비를 입력해주세요."
    private const val INPUT_MINES = "지뢰는 몇 개인가요?"
    private const val INPUT_NUMBER_ERROR = "숫자만 입력해주세요."
    private const val DELIMITER = ","
    private const val INPUT_OPEN = "open : "
}
