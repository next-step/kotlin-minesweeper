package view

import domain.MineSweeperBoard

object ResultView {
    fun startMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun board(board: MineSweeperBoard) {
        repeat(board.boardSize.height) { y ->
            board.board.slice(y * board.boardSize.width until (y + 1) * board.boardSize.width)
                .joinToString(" ") { it.toString() }.also {
                    println(it)
                }
        }
    }
}
