package minesweeper.view

import minesweeper.domain.Empty
import minesweeper.domain.Mine
import minesweeper.domain.MineMap
import minesweeper.domain.Position
import minesweeper.domain.MapItem


class ResultView {

    fun outputGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun outputMap(mineMap: MineMap) {
        println(getMapString(mineMap))
    }

    fun outputResult(mineMap: MineMap) {
        if (mineMap.mineOpened) {
            println("Lose Game")
        }
        if (mineMap.checkAllEmptyOpened()) {
            println("Win Game")
        }
    }

    private fun getMapString(mineMap: MineMap): String {
        val map = mineMap.getCurrentMap()
        return buildString {
            repeat(mineMap.height) { y ->
                repeat(mineMap.width) { x ->
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
