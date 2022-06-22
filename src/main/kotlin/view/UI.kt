package view

import domain.Coordinate
import domain.Mine

object UI {
    fun displays(boards: Array<Array<Coordinate>>) {
        for (board in boards) {
            display(board)
            println()
        }
    }

    private fun display(board: Array<Coordinate>) {
        board.map { value -> if (value is Mine) print("X") else print("O") }
    }
}
