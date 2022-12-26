package view

import domain.Board
import domain.Land
import domain.Mine

object OutputView {
    private const val START_MINE_SWEEPER = "지뢰찾기 게임 시작"
    private const val LAND_STRING = "C"
    private const val MINE_STRING = "*"

    fun printBoard(board: Board) {
        println(START_MINE_SWEEPER)
        (0 until board.height.value).forEach { height ->
            (0 until board.width.value).forEach { width ->
                printField(board, height, width)
            }
            println()
        }
    }

    private fun printField(board: Board, height: Int, width: Int) {
        when (board.getField(height, width)) {
            is Land -> print("${board.getNearByMineCount(height, width)} ")
            is Mine -> print("$MINE_STRING ")
        }
    }
}
