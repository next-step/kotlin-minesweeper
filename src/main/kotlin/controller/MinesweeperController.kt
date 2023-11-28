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
        val isWin = processGame(mineMap)
        OutputView.printResultMessage(isWin)
    }

    private fun inputCondition(): MineMapInfo =
        MineMapInfo(
            Point(
                InputView.inputHeight(),
                InputView.inputWidth()
            ),
            InputView.inputMineCount()
        )

    private fun openSpot(mineMap: MineMap): MineStatus {
        val openSpot = InputView.inputOpenSpot()
        val mineStatus = mineMap.open(openSpot)
        OutputView.printMineMap(mineMap)
        return mineStatus
    }

    private fun processGame(mineMap: MineMap): Boolean {
        var isWin = true
        while (mineMap.noMoreOpenSpot().not()) {
            if (openSpot(mineMap) == MineStatus.MINED) {
                isWin = false
                break
            }
        }

        return isWin
    }
}
