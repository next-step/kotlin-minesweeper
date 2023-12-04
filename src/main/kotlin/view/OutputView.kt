package view

import domain.board.Board
import domain.board.Cell

object OutputView {
    fun printBoard(board: Board) {
        println("지뢰찾기 게임 시작")
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
