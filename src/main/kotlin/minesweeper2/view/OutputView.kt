package minesweeper2.view

import minesweeper2.domain.MineTile
import minesweeper2.domain.PlainTile
import minesweeper2.domain.Tile
import minesweeper2.domain.TileRow

object OutputView {

    fun showGameStartMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun showMapInProgress(mineMap: List<TileRow>) {
        mineMap.forEach {
            println(getTilesRow(it))
        }
    }

    private fun getTilesRow(tileRow: TileRow?): String {
        if (tileRow == null) return ""
        val stringBuilder = StringBuilder()
        repeat(tileRow.size) {
            val mark = getTileMark(tileRow[it])
            stringBuilder.append("$mark ")
        }
        return stringBuilder.toString()
    }

    private fun getTileMark(tile: Tile): String {
        if (!tile.isCheckedTile) {
            return "C"
        }
        return when (tile) {
            is MineTile -> "*"
            is PlainTile -> tile.nearMineCount.toString()
            else -> ""
        }
    }

    fun showGameResult(isWin: Boolean) {
        println(
            if (isWin) "Win Game." else "Lose Game."
        )
    }
}
