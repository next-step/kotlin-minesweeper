package view

import domain.Height
import domain.MineCount
import domain.Position
import domain.Width

object InputView {
    fun inputHeight(): Height {
        println("높이를 입력하세요.")
        val height = Height(readln().toInt())
        println()
        return height
    }

    fun inputWidth(): Width {
        println("너비를 입력하세요.")
        val width = Width(readln().toInt())
        println()
        return width
    }

    fun inputMineCount(): MineCount {
        println("지뢰는 몇 개인가요?")
        val mineCount = MineCount(readln().toInt())
        println()
        return mineCount
    }

    fun inputOpenPosition(): Position {
        print("open: ")
        val input = readln()
        val (x, y) = input.splitPosition()
        return Position.of(x, y)
    }

    private fun String.splitPosition(): List<Int> = this.split(DELIMITER_POSITION).map { it.trim().toInt() }

    private const val DELIMITER_POSITION = ","
}
