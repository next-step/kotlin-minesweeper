package minesweeper.presentation

import minesweeper.domain.Board

object ResultView {
    fun printBoard(board: Board) {
        board.forEach { column ->
            println(column.joinToString(" ", "", "") { it.symbol })
        }
    }
}
