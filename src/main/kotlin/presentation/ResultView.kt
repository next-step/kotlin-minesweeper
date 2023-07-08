package presentation

import domain.Board
import domain.Row
import domain.Space

object ResultView {
    fun printBoard(board: Board) {
        board.rows.forEach {
            printRow(it)
        }
    }

    private fun printRow(row: Row) {
        row.spaces.forEach {
            printSpace(it)
        }
        println()
    }

    private fun printSpace(space: Space) {
        when (space) {
            is Space.Mine -> print("*")
            is Space.Empty -> print("C")
        }
        print(" ")
    }
}
