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
        val heightSize = InputView.inputHeight() ?: DEFAULT
        val widthSize = InputView.inputWidth() ?: DEFAULT
        val height = Height(size = heightSize)
        val width = Width(size = widthSize)

        val map = Map.create(height = height, width = width)

        val mineCount = MineCount(count = InputView.inputMineCount() ?: DEFAULT)

        ResultView.gameStart()
        val minePoints = MinePoints.create(heightSize = heightSize, widthSize = widthSize).take(mineCount)
        minePoints.forEach { map.placeMine(it) }

        ResultView.printMap(map.grid)
    }

    companion object {
        private const val DEFAULT = 0
    }
}
