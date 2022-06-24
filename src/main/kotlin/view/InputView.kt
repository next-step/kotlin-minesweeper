package view

import domain.MinesCounter

object InputView {
    tailrec fun getWidth(): Int {
        println(WIDTH_INPUT_MESSAGE)
        return readln().toIntOrNull() ?: getWidth()
    }

    tailrec fun getHeight(): Int {
        println(HEIGHT_INPUT_MESSAGE)
        return readln().toIntOrNull() ?: getHeight()
    }

    tailrec fun getMinesCount(): MinesCounter {
        println(MINESWEEPER_INPUT_MESSAGE)
        val value = readln().toInt()
        return MinesCounter(value)
    }

    const val WIDTH_INPUT_MESSAGE = "너비를 입력하세요."
    const val HEIGHT_INPUT_MESSAGE = "높이를 입력하세요."
    const val MINESWEEPER_INPUT_MESSAGE = "지뢰는 몇 개인가요?"
}
