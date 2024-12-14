package console

import map.Height
import map.Map
import map.Width
import map.move.Position
import mine.MineCount
import mine.MinePoints
import open.result.OpenResult
import view.InputView
import view.ResultView

class MineSweeperConsole(
    var map: Map = Map.create(height = Height(size = 0), width = Width(size = 0)),
) {
    fun start() {
        val height = Height(size = InputView.inputHeight() ?: DEFAULT)
        val width = Width(size = InputView.inputWidth() ?: DEFAULT)

        map = Map.create(height = height, width = width)

        val mineCount = MineCount(count = InputView.inputMineCount() ?: DEFAULT)

        ResultView.gameStart()
        val minePoints = MinePoints.create(height = height, width = width, mineCount = mineCount)
        map.placeMine(minePoints)

        map = map.updateMineCountByCell()
        while (true) {
            val searchPosition =
                InputView.inputSearchPosition(height = height, width = width)
                    ?: Position.default(height = height, width = width)

            when (val result = map.open(position = searchPosition)) {
                is OpenResult.Success -> map = result.map
                is OpenResult.InvalidPosition -> Unit
                is OpenResult.MineExploded -> {
                    ResultView.printLose()
                    return
                }
            }

            ResultView.printMap(map)
        }
    }

    companion object {
        private const val DEFAULT = 0
    }
}