package minesweeper.view

import minesweeper.view.model.CoordinateView

object InputView {

    private const val ENTER_WIDTH_MESSAGE: String = "높이를 입력하세요."
    private const val ENTER_HEIGHT_MESSAGE: String = "\n너비를 입력하세요."
    private const val ENTER_MINE_COUNT_MESSAGE: String = "\n지뢰는 몇 개인가요?"
    private const val ENTER_OPEN_COORDINATE: String = "open: "

    fun readWidth(): Int {
        println(message = ENTER_WIDTH_MESSAGE)
        return readln().toInt()
    }

    fun readHeight(): Int {
        println(message = ENTER_HEIGHT_MESSAGE)
        return readln().toInt()
    }

    fun readMineCount(): Int {
        println(message = ENTER_MINE_COUNT_MESSAGE)
        return readln().toInt()
    }

    fun readOpenCoordinate(): CoordinateView {
        print(message = ENTER_OPEN_COORDINATE)
        return CoordinateView(inputCoordinate = readln())
    }
}
