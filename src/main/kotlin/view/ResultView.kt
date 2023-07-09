package view

import domain.*

object ResultView {

    private const val GAME_START_STRING = "\n지뢰찾기 게임 시작"
    private const val LOCATION_START_NUM = 0
    private const val MINE_STRING = "*"
    private const val BASIC_STRING = "C"

    fun printGameStart() {
        println(GAME_START_STRING)
    }

    fun printGameBoard(height: Int, width: Int, minesWeeper: MinesWeeper) {
        (LOCATION_START_NUM until height)
            .forEach { y ->
                (LOCATION_START_NUM until width)
                    .forEach { x ->
                        val cell = minesWeeper.findBoard(Location(x, y))?.cell
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
            is Basic -> getBasicString(cell)
        }
    }

    private fun getBasicString(basic: Basic): String {
        if (basic.isOpen) return basic.count.toString()
        return BASIC_STRING
    }
}
