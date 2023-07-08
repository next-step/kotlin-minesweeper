package minesweeper.view

import minesweeper.domain.Empty
import minesweeper.domain.Mine
import minesweeper.domain.MineMap
import minesweeper.domain.Position

class ResultView {

    fun outputGameStart(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        println(getMapString(mineMap))
    }

    private fun getMapString(mineMap: MineMap): String {
        val map = mineMap.getCurrentMap()
        return buildString {
            repeat(mineMap.height) { y ->
                repeat(mineMap.width) { x ->
                    val mapItem = map[Position(x, y)] ?: Empty()
                    val symbol = when (mapItem) {
                        is Empty -> EMPTY_SYMBOL
                        is Mine -> MINE_SYMBOL
                    }
                    append("$symbol ")
                }
                appendLine()
            }
        }
    }

    companion object {
        const val MINE_SYMBOL = "*"
        const val EMPTY_SYMBOL = "C"
    }
}
