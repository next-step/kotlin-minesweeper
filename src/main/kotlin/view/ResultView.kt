package view

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
            val displayValue = if (cell.isMine) MINE_INDICATOR else cell.displayValue
            print("$displayValue ")
        }
        println()
    }

    companion object {
        private const val MINE_INDICATOR = "*"
    }
}
