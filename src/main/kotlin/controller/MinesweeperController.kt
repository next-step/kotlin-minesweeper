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

        OutputView.outputGameStart()
        processGame(mineMap)
    }

    private fun inputCondition(): MineMapInfo =
        MineMapInfo(
            Point(
                InputView.inputHeight(),
                InputView.inputWidth()
            ),
            InputView.inputMineCount()
        )

    private fun processGame(mineMap: MineMap) {
        val openSpot = InputView.inputOpenSpot()
        mineMap.open(openSpot)
        OutputView.printMineMap(mineMap)
    }
}
