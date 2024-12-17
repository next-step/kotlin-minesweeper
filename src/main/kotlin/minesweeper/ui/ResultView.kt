package minesweeper.ui

import minesweeper.domain.CompletedGame
import minesweeper.domain.EmptyCell
import minesweeper.domain.MineExplodedGame
import minesweeper.domain.MinedCell
import minesweeper.domain.PlayableBoard
import minesweeper.domain.PlayerWonGame

object ResultView {
    private val NEWLINE = System.lineSeparator()
    private const val GAME_START = "지뢰찾기 게임 시작"
    private const val SPACE = " "
    private const val MINE = "*"

    fun start() {
        buildString {
            appendLine()
            appendLine(GAME_START)
        }.also { print(it) }
    }

    fun render(board: PlayableBoard) {
        println(board.render())
    }

    private fun PlayableBoard.render(): String {
        val maxY = cells.keys.maxOf { it.y }
        val maxX = cells.keys.maxOf { it.x }
        return (0..maxY).joinToString(NEWLINE) { y ->
            (0..maxX).joinToString(SPACE) { x ->
                when (get(y, x)) {
                    is MinedCell -> MINE
                    is EmptyCell -> countMines(y, x).toString()
                    null -> error("정상적으로 판이 생성되었으면 도달할 수 없는 코드")
                }
            }
        }
    }

    fun result(game: CompletedGame) {
        when (game) {
            is PlayerWonGame -> println("Won Game.")
            is MineExplodedGame -> println("Lose Game.")
        }
    }
}
