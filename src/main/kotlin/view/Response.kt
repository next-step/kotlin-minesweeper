package view

import Squares

object Response {

    fun startView(board: List<Squares>) {
        board.forEach { squares ->
            println(squares.squares.joinToString(" ") { it.display() })
        }
    }
}
