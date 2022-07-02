package minesweeper.presentation

import minesweeper.domain.Area
import minesweeper.domain.Coordinate
import minesweeper.domain.InputView
import minesweeper.domain.MineCount

object InputReceiver : InputView {

    override fun receiveBoardSize(): Area {
        val height = receiverHeight()
        println()
        val width = receiverWidth()
        println()

        return Area(height, width)
    }

    private fun receiverHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    private fun receiverWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    override fun receiveMineCount(): MineCount {
        println("지뢰는 몇 개인가요?")
        return MineCount(readln().toInt())
    }

    override fun receiveOpenCoordinate(): Coordinate {
        print("open: ")
        val (y, x) = readln().replace(" ", "").split(",").map(String::toInt)
        return Coordinate(x, y)
    }
}
