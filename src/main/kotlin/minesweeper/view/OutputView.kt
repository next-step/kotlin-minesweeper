package minesweeper.view

import minesweeper.domain.MineMap
import minesweeper.domain.TileRow

object OutputView {

    fun showGameResult(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        mineMap.mineMap.forEach {
            println(getTilesColumn(it))
        }
    }

    private fun getTilesColumn(tileRow: TileRow?): String {
        if (tileRow == null) return ""
        val stringBuilder = StringBuilder()
        repeat(tileRow.size) {
            val tile = getTile(tileRow, it)
            stringBuilder.append(tile)
        }
        return stringBuilder.toString()
    }

    private fun getTile(tileRow: TileRow, position: Int): String {
        var tile = "C "
        if (tileRow[position].isMine()) {
            tile = "* "
        }
        return tile
    }
}
