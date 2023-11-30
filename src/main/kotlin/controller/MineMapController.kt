package controller

import domain.MineMap
import domain.OpenStatus
import domain.Point
import view.View

class MineMapController {

    fun run() {
        val height = View.inputHeight()
        val width = View.inputWidth()
        val mineCount = View.inputMineCount()
        val mineMap = MineMap(Point(height, width), mineCount)

        View.outputStartGame()

        while (mineMap.isAllOpened().not()) {
            val openPoint = View.inputOpenSpot()
            val openStatus = mineMap.open(openPoint)

            if (openStatus == OpenStatus.MINE) {
                View.outputLoseGame()
                return
            }
        }

        View.outputWinGame()
    }
}