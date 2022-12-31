package minesweeper

import minesweeper.domain.Map
import minesweeper.domain.MapMeta
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MinesWeeperGame {
    fun play() {
        ResultView.printMessage(ResultView.Message.REQUEST_HEIGHT)
        val height = InputView.requestPositiveNumber()

        ResultView.printMessage(ResultView.Message.REQUEST_WIDTH)
        val width = InputView.requestPositiveNumber()

        ResultView.printMessage(ResultView.Message.REQUEST_MINE)
        val mineCount = InputView.requestPositiveNumber()

        ResultView.printMessage(ResultView.Message.START)
        val meta = MapMeta(
            height = height,
            width = width,
            mineCount = mineCount
        )
        val map = Map.create(meta)

        ResultView.printMap(map.cells, width)
    }
}

fun main() {
    MinesWeeperGame().play()
}
