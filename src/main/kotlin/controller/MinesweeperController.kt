package controller

import domain.MineMap
import domain.MineMapGenerator
import view.InputView
import view.OutputView
import vo.MineMapInfo

class MinesweeperController {

    fun run() {
        val mapInfo = inputCondition()
        val mineMap = MineMap(MineMapGenerator.newMap(mapInfo))

        OutputView.outputGameStart(mineMap)
    }

    private fun inputCondition(): MineMapInfo =
        MineMapInfo(
            InputView.inputHeight(),
            InputView.inputWidth(),
            InputView.inputMineCount()
        )
}
