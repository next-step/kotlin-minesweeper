package ui

import domain.MinesweeperGame
import domain.SlotInfo

class MinesweeperGameView {

    fun printField(game: MinesweeperGame) {
        var fieldStr = ""
        game.field.forEach { slotInfos: Array<SlotInfo> ->
            var lineStr = ""
            slotInfos.forEach { lineStr += "${if (it.isMineExist) "*" else "C"} " }
            fieldStr += "${lineStr}\n"
        }
        println(fieldStr)
    }
}
