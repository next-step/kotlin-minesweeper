package controller

import domain.MineMap
import domain.MineMapInfo
import domain.Point
import view.View

class MineMapController {

    fun run() {
        val mineMap = inputMineMapData()
        View.outputStartGame()

        while (doRound(mineMap)) { }

        outputResult(isWin = mineMap.isAllOpened(), mineMap)
    }

    private fun inputMineMapData(): MineMap {
        val height = View.inputHeight()
        val width = View.inputWidth()
        val mineCount = View.inputMineCount()
        val mapInfo = MineMapInfo(Point(height, width), mineCount)
        return MineMap(mapInfo)
    }

    private fun outputResult(isWin: Boolean, mineMap: MineMap) {
        View.outputMineMap(mineMap)
        if (isWin) View.outputWinGame()
        else View.outputLoseGame()
    }

    private fun doRound(mineMap: MineMap): Boolean {
        if (mineMap.isAllOpened()) return false
        View.outputMineMap(mineMap)
        val openPoint = View.inputOpenSpot()
        val openStatus = mineMap.open(openPoint)

        return !openStatus.mineTrapped()
    }
}
