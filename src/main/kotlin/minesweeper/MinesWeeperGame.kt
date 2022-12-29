package minesweeper

import minesweeper.domain.Map
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MinesWeeper {
    fun play() {
        ResultView.printMessage(ResultView.Message.REQUEST_HEIGHT)
        val height = InputView.requestPositiveNumber()

        ResultView.printMessage(ResultView.Message.REQUEST_WIDTH)
        val with = InputView.requestPositiveNumber()

        ResultView.printMessage(ResultView.Message.REQUEST_MINE)
        val mineCount = InputView.requestPositiveNumber()

        ResultView.printMessage(ResultView.Message.START)
        val map = Map.create(
            height = height,
            width = with,
            mineCount = mineCount
        )

        ResultView.printMap(map.cells, with)
    }
}

fun main() {
    MinesWeeper().play()
}
