package minesweeper.ui

import minesweeper.domain.Board
import minesweeper.domain.Coordinate
import minesweeper.domain.EmptyCell
import minesweeper.domain.MinedCell

object ResultView {
    private val NEWLINE = System.lineSeparator()
    private const val GAME_START = "지뢰찾기 게임 시작"
    private const val SPACE = " "
    private const val MINE = "*"
    private const val CLOSED = "C"

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
                val coordinate = Coordinate(y, x)
                when (cells[coordinate]) {
                    is MinedCell -> MINE
                    is EmptyCell -> CLOSED
                    null -> error("판에서 ${coordinate}의 칸이 빠저있습니다.")
                }
            }
        }
    }
}
