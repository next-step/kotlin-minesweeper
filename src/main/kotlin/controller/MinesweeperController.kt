package controller

import domain.MineMap
import domain.MineMapGenerator
import view.InputView
import view.OutputView
import vo.MineMapInfo
import vo.Point

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
