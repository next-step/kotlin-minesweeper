package minesweeper.view

import minesweeper.domain.MineMap
import minesweeper.domain.TileColumn

object OutputView {

    fun showGameResult(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        mineMap.mineMap.forEach {
            println(getTilesColumn(it))
        }
    }

    private fun getTilesColumn(tileColumn: TileColumn?): String {
        if(tileColumn == null) return ""
        val stringBuilder = StringBuilder()
        repeat(tileColumn.size) {
            val tile = getTile(tileColumn, it)
            stringBuilder.append(tile)
        }
        return stringBuilder.toString()
    }

    private fun getTile(tileColumn: TileColumn, position: Int): String {
        var tile = "C "
        if (tileColumn[position].isMine()) {
            tile = "* "
        }
        return tile
    }
}
