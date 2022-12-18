package view

import domain.Board

object OutputView {
    private const val START_MINE_SWEEPER = "지뢰찾기 게임 시작"
    fun printBoard(board: Board) {
        println(START_MINE_SWEEPER)
        List(board.height.value) { height ->
            List(board.width.value) { width ->
                print("${board.getField(height, width)} ")
            }
            println()
        }
    }
}
