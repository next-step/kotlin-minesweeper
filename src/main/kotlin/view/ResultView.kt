package view

import domain.Basic
import domain.Cell
import domain.Location
import domain.MinesWeeper

object ResultView {

    private const val GAME_START_STRING = "\n지뢰찾기 게임 시작"
    private const val LOCATION_START_NUM = 0
    private const val BASIC_STRING = "C"
    private const val LOSE_STRING = "Lose game."

    fun printGameStart() {
        println(GAME_START_STRING)
    }

    fun printGameBoard(height: Int, width: Int, minesWeeper: MinesWeeper) {
        (LOCATION_START_NUM until height)
            .forEach { y ->
                (LOCATION_START_NUM until width)
                    .mapNotNull { x ->
                        minesWeeper.findBoard(Location(x, y))
                    }.forEach {
                        print("${getPrintString(it.cell)} ")
                    }
                println()
            }
        println()
    }

    private fun getPrintString(cell: Cell): String {
        if (cell is Basic && cell.isOpen) {
            return cell.count.toString()
        }
        return BASIC_STRING
    }

    fun printLose() {
        println(LOSE_STRING)
    }
}
