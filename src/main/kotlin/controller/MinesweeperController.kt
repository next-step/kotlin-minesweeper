package controller

import domain.MineMap
import domain.RandomMineMap
import domain.field.MineMapInfo
import domain.field.Point
import view.InputView
import view.OutputView

class MinesweeperController {

    fun run() {
        val mapInfo = inputCondition()
        val mineMap = MineMap(RandomMineMap.newMap(mapInfo))

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
