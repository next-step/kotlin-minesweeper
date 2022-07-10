package minesweeper

import minesweeper.entity.MapInformation
import minesweeper.entity.MineMap
import minesweeper.ui.Input
import minesweeper.ui.Result

object MineSweeper {
    fun play() {
        val mapInfo = MapInformation(Input.getHeight(), Input.getWidth(), Input.getNumberOfMine())
        val mineMap = MineMap.getCountedMap(mapInfo)
        Result.informPlay(mineMap)
    }
}
