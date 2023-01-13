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

        playGame(map, width)
    }

    private fun playGame(map: Map, width: Int) {
        var nextMap = map

        while (nextMap.isProcessing()) {
            ResultView.printMessage(ResultView.Message.OPEN)
            val cellPosition = InputView.requestCellPosition()
            nextMap = nextMap.open(cellPosition)
            val status = nextMap.status

            if (!status.isProcess()) {
                ResultView.printResult(status)
                return
            }

            ResultView.printMap(nextMap.cells, width)
        }
    }
}

fun main() {
    MinesWeeperGame().play()
}
