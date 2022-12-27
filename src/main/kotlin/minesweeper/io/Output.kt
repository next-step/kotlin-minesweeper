package minesweeper.io

import minesweeper.domain.Board

class Output {
    fun printBoard(board: Board) {
        println(board.matrix.joinToString("\n") { it.joinToString(" ") })
    }
}
