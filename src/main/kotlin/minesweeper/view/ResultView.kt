package minesweeper.view

import minesweeper.domain.Position
import minesweeper.domain.minemap.Empty
import minesweeper.domain.minemap.MapItem
import minesweeper.domain.minemap.Mine
import minesweeper.domain.minemap.MineMap

class ResultView {

    fun outputGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun outputMap(mineMap: MineMap) {
        println(getMapString(mineMap))
    }

    fun outputResult(win: Boolean) {
        if (win) {
            println("Win Game")
        } else {
            println("Lose Game")
        }
    }

    private fun getMapString(mineMap: MineMap): String {
        val map = mineMap.currentMap()
        return buildString {
            repeat(mineMap.mineMapConfig.height) { y ->
                repeat(mineMap.mineMapConfig.width) { x ->
                    val mapItem = map[Position(x, y)] ?: Empty()
                    append("${getMapItemString(mapItem)} ")
                }
                appendLine()
            }
        }
    }

    private fun getMapItemString(mapItem: MapItem): String {
        if (mapItem.isOpened.not()) return HIDE_SYMBOL
        return when (mapItem) {
            is Empty -> mapItem.surroundingMineCount.toString()
            is Mine -> MINE_SYMBOL
        }
    }

    companion object {
        const val MINE_SYMBOL = "*"
        const val HIDE_SYMBOL = "C"
    }
}
