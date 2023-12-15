package view

import gamemap.Cell
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

    fun printGameOver(gameState: GameState) {
        require(gameState is GameState.GameOver)
        when (gameState) {
            GameState.Win -> println("Game Over: You win!")
            GameState.Lose -> println("Game Over: You lose")
        }
    }

    private fun Cell.displayValue() =
        if (isClose()) CLOSE_DISPLAY_CHARACTER else if (isMine) MINE_INDICATOR else adjacentMineCount

    companion object {
        private const val MINE_INDICATOR = "*"
        const val CLOSE_DISPLAY_CHARACTER = "C"
    }
}
