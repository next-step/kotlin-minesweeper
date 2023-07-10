package presentation

import domain.Board
import domain.Spaces
import domain.Space

object ResultView {
    fun printBoard(board: Board) {
        board.rows.forEach {
            printRow(it)
        }
    }

    private fun printRow(spaces: Spaces) {
        spaces.list().forEach {
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
