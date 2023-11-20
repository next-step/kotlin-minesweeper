package controller

import domain.MineMap
import view.View
import vo.MineMapInfo

class MinesweeperController {

    fun run() {
        val mapInfo = inputCondition()
        val mineMap = MineMap(mapInfo)

        View.outputGameStart(mineMap)
    }

    private fun inputCondition(): MineMapInfo =
        MineMapInfo(
            View.inputHeight(),
            View.inputWidth(),
            View.inputMineCount()
        )
}
