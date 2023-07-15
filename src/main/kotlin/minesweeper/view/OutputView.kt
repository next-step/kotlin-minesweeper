package minesweeper.view

import minesweeper.domain.GameStatus
import minesweeper.domain.MineRow
import minesweeper.domain.MinesMap
import minesweeper.domain.Tile

object OutputView {

    fun showGameStartMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun showMapInProgress(mineMap: MinesMap) {
        mineMap.columns.forEach {
            println(getTilesRow(it))
        }
    }

    private fun getTilesRow(tileRow: MineRow): String {
        val stringBuilder = StringBuilder()
        repeat(tileRow.size) {
            val mark = getTileMark(tileRow[it])
            stringBuilder.append("$mark ")
        }
        return stringBuilder.toString()
    }

    private fun getTileMark(tile: Tile): String {
        if (tile.isMine || !tile.isOpen) {
            return "C"
        }
        return tile.nearMineCount.toString()
    }

    fun showGameResult(gameResult: GameStatus) {
        println(
            when (gameResult) {
                GameStatus.WIN -> "Win Game."
                GameStatus.LOSE -> "Lose Game."
                else -> return
            }
        )
    }
}
