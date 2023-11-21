package ui

import domain.MinesweeperGame
import domain.SlotInfo

class MinesweeperGameView {

    fun printField(game: MinesweeperGame) {
        val fieldStr: String = buildString {
            game.field.forEach { slotInfos: Array<SlotInfo> ->
                val lineStr = buildString {
                    slotInfos.forEach { slotInfo: SlotInfo ->
                        append(
                            if (slotInfo.isMineExist) "*" else "C"
                        )
                        append(" ")
                    }
                }
                appendLine(lineStr)
            }
        }
        println(fieldStr)
    }
}
