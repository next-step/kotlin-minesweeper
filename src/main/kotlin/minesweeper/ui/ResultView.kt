package minesweeper.ui

import minesweeper.domain.Board
import minesweeper.domain.EmptyCell
import minesweeper.domain.MinedCell

object ResultView {
    private val NEWLINE = System.lineSeparator()
    private const val GAME_START = "지뢰찾기 게임 시작"
    private const val SPACE = " "
    private const val MINE = "*"

    fun render(board: Board) {
        val message =
            buildString {
                appendLine()
                appendLine(GAME_START)
                appendLine(board.render())
            }
        println(message)
    }

    private fun Board.render(): String {
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
}
