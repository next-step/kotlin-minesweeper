package view

import domain.Board
import domain.Field
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
                printField(board.getField(height, width))
            }
            println()
        }
    }

    private fun printField(field: Field) {
        return when (field) {
            is Land -> print(LAND_STRING)
            is Mine -> print(MINE_STRING)
        }
    }
}
