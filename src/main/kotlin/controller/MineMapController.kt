package controller

import domain.MineMap
import domain.MineMapInfo
import domain.Point
import view.View

class MineMapController {

    fun run() {
        val height = View.inputHeight()
        val width = View.inputWidth()
        val mineCount = View.inputMineCount()
        val mapInfo = MineMapInfo(Point(height, width), mineCount)
        val mineMap = MineMap(mapInfo)

        View.outputStartGame()

        while (mineMap.isAllOpened().not()) {
            View.outputMineMap(mineMap)
            val openPoint = View.inputOpenSpot()
            val openStatus = mineMap.open(openPoint)

            if (openStatus.mineTrapped()) {
                View.outputLoseGame()
                return
            }
        }

        View.outputMineMap(mineMap)
        View.outputWinGame()
    }
}
