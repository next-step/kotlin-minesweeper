package view

import Squares

object Response {

    fun startView(board: List<Squares>) {
        println("지뢰찾기 게임 시작")

        board.forEach { squares ->
            println(squares.squares.joinToString(" ") { it.display() })
        }
    }
}
