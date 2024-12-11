package minesweeper.view

import minesweeper.entity.Coordinate
import minesweeper.entity.Height
import minesweeper.entity.MineCount
import minesweeper.entity.Width

class InputView {
    fun inputHeight(): Height {
        println("높이를 입력해주세요.")
        val height = readln().toInt()
        return Height(height)
    }

    fun inputWidth(): Width {
        println("너비를 입력해주세요.")
        val width = readln().toInt()
        return Width(width)
    }

    fun inputMineCount(): MineCount {
        println("지뢰 개수를 입력해주세요.")
        val mineCount = readln().toInt()
        return MineCount(mineCount)
    }

    fun inputCoordinate(): Coordinate {
        print("open: ")
        val (x, y) = readln().split(",").map { it.trim().toInt() }
        return Coordinate(x, y)
    }
}
