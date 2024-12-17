package console

import console.round.Game
import map.Height
import map.Map
import map.Width
import minecount.MineCount
import minecount.MinePoints
import view.InputView
import view.ResultView

class MineSweeperConsole {
    fun start() {
        val height = Height(size = InputView.inputHeight() ?: DEFAULT)
        val width = Width(size = InputView.inputWidth() ?: DEFAULT)

        val map = Map.create(height = height, width = width)

        val mineCount = MineCount(count = InputView.inputMineCount() ?: DEFAULT)

        ResultView.gameStart()
        val minePoints = MinePoints.create(height = height, width = width, mineCount = mineCount)
        map.placeMine(minePoints)

        val result =
            Game
                .init(map = map.updateMineCountByCell())
                .start(
                    choosePosition = { searchHeight, searchWidth -> InputView.inputSearchPosition(searchHeight, searchWidth) },
                    afterRound = { mapForPint -> ResultView.printMap(mapForPint) },
                )

        if (result.isLose()) ResultView.printLose()
        return
    }

    companion object {
        private const val DEFAULT = 0
    }
}
