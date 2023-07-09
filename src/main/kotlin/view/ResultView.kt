package view

import domain.Basic
import domain.Cell
import domain.Mine
import domain.MinesWeeper

object ResultView {

    private const val BOARD_PRINT_STRING = "\n지뢰찾기 게임 시작"
    private const val LOCATION_START_NUM = 0
    private const val MINE_STRING = "*"

    fun printGameBoard(height: Int, width: Int, minesWeeper: MinesWeeper) {
        println(BOARD_PRINT_STRING)
        (LOCATION_START_NUM until height)
            .forEach { y ->
                (LOCATION_START_NUM until width)
                    .forEach { x ->
                        val cell = minesWeeper.findBoard(y, x)?.cell
                        if (cell is Cell) {
                            print("${getPrintString(cell)} ")
                        }
                    }
                println()
            }
    }

    private fun getPrintString(cell: Cell): String {
        return when (cell) {
            is Mine -> MINE_STRING
            is Basic -> cell.count.toString()
        }
    }
}
