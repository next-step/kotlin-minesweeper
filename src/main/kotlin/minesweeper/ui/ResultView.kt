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
        val maxY = cells.maxOf { it.coordinate.y }
        val maxX = cells.maxOf { it.coordinate.x }
        return (0..maxY).joinToString(NEWLINE) { y ->
            (0..maxX).joinToString(SPACE) { x ->
                val cell = cells.first { it.coordinate == Coordinate(y, x) }
                when (cell) {
                    is MinedCell -> MINE
                    is EmptyCell -> CLOSED
                }
            }
        }
    }
}
