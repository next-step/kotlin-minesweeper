package controller

import domain.MineMap
import view.InputView
import view.OutputView
import vo.MineMapInfo

class MinesweeperController {

    fun run() {
        val mapInfo = inputCondition()
        val mineMap = MineMap(mapInfo)

        OutputView.outputGameStart(mineMap)
    }

    private fun inputCondition(): MineMapInfo =
        MineMapInfo(
            InputView.inputHeight(),
            InputView.inputWidth(),
            InputView.inputMineCount()
        )
}
