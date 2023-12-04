package view

import domain.board.Board
import domain.board.Cell

object OutputView {
    fun printBoard(board: Board) {
        board.value.forEach {
            it.forEach { cell ->
                print("${makeCellShape(cell)} ")
            }
            println()
        }
    }

    private fun makeCellShape(cell: Cell) = when (cell) {
        is Cell.Mine -> "*"
        is Cell.Tile -> "C"
    }
}
