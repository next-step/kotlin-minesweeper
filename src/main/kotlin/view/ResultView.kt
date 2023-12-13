package view

import gamemap.Cell
import gamemap.CellState
import gamemap.GameMap

class ResultView {
    fun printGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printGameMap(gameMap: GameMap) {
        repeat(gameMap.height) { r ->
            printGameMapRow(gameMap, r)
        }
    }

    private fun printGameMapRow(gameMap: GameMap, r: Int) {
        repeat(gameMap.width) { c ->
            val cell = gameMap.cellAt(r, c)
            val displayValue = cell.displayValue()
            print("$displayValue ")
        }
        println()
    }

    private fun Cell.displayValue() = if (isMine) MINE_INDICATOR else if (state == CellState.Close) CLOSE_DISPLAY_CHARACTER else adjacentMineCount

    companion object {
        private const val MINE_INDICATOR = "*"
        const val CLOSE_DISPLAY_CHARACTER = "C"
    }
}
