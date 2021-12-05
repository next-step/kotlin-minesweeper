package minesweeper.view

import minesweeper.model.Column
import minesweeper.model.Height
import minesweeper.model.MineCount
import minesweeper.model.Position
import minesweeper.model.Row
import minesweeper.model.Width

class InputView {

    fun getHeight(): Height {
        println("높이를 입력하세요")
        val height = Height.valueOf(readLine()?.toIntOrNull())
        println()
        return height
    }

    fun getWidth(): Width {
        println("너비를 입력하세요")
        val width = Width.valueOf(readLine()?.toIntOrNull())
        println()
        return width
    }

    fun getMineCount(): MineCount {
        println("지뢰는 몇 개 인가요?")
        val mineCount = MineCount.valueOf(readLine()?.toIntOrNull())
        println()
        return mineCount
    }

    fun getOpenPosition(): Position {
        println()
        print("open: ")
        val (row, column) = readLine()!!
            .split(", ")
            .map { it.toInt() }
        return Position.of(Row(row), Column(column))
    }
}
