package controller

import domain.MineMap
import domain.MineMapGenerator
import domain.MineMapInfo
import domain.Point
import view.InputView
import view.OutputView

class MinesweeperController {

    fun run() {
        val mapInfo = inputCondition()
        val mineMap = MineMap(MineMapGenerator.newMap(mapInfo))

        OutputView.outputGameStart(mineMap)
    }

    private fun inputCondition(): MineMapInfo =
        MineMapInfo(
            Point(
                InputView.inputHeight(),
                InputView.inputWidth()
            ),
            InputView.inputMineCount()
        )
}
