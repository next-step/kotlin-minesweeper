package console

import map.Height
import map.Map
import map.Width
import mine.MineCount
import mine.MinePoints
import view.InputView
import view.ResultView

class MineSweeperConsole {
    fun start() {
        val height = Height(size = InputView.inputHeight() ?: DEFAULT)
        val width = Width(size = InputView.inputWidth() ?: DEFAULT)

        val map = Map.create(height = height, width = width)

        val mineCount = MineCount(count = InputView.inputMineCount() ?: DEFAULT)

        ResultView.gameStart()
        val minePoints = MinePoints.create(height = height, width = width).take(mineCount)
        map.placeMine(minePoints)

        ResultView.printMap(map.grid)
    }

    companion object {
        private const val DEFAULT = 0
    }
}
