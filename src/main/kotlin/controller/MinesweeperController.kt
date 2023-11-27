package controller

import domain.MineMap
import domain.RandomMineMap
import domain.field.MineMapInfo
import domain.field.Point
import domain.status.MineStatus
import view.InputView
import view.OutputView

class MinesweeperController {

    fun run() {
        val mapInfo = inputCondition()
        val mineMap = MineMap(RandomMineMap.newMap(mapInfo))

        OutputView.outputGameStart()
        while (processGame(mineMap) != MineStatus.MINED) {}
    }

    private fun inputCondition(): MineMapInfo =
        MineMapInfo(
            Point(
                InputView.inputHeight(),
                InputView.inputWidth()
            ),
            InputView.inputMineCount()
        )

    private fun processGame(mineMap: MineMap): MineStatus {
        val openSpot = InputView.inputOpenSpot()
        val mineStatus = mineMap.open(openSpot)
        OutputView.printMineMap(mineMap)
        return mineStatus
    }
}
