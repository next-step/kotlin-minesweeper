package minesweeper.view

import minesweeper.model.Height
import minesweeper.model.MineCount
import minesweeper.model.Width

class InputView {

    fun getHeight(): Height {
        println("높이를 입력하세요")
        val height = readLine()?.toInt()?.let { Height(it) } ?: Height.ZERO
        println()
        return height
    }

    fun getWidth(): Width {
        println("너비를 입력하세요")
        val width = readLine()?.toInt()?.let { Width(it) } ?: Width.ZERO
        println()
        return width
    }

    fun getMineCount(): MineCount {
        println("지뢰는 몇 개 인가요?")
        val mineCount = readLine()?.toInt()?.let { MineCount(it) } ?: MineCount.ZERO
        println()
        return mineCount
    }
}
