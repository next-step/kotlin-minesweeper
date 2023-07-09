package view

import domain.*

object ResultView {

    private const val GAME_START_STRING = "\n지뢰찾기 게임 시작"
    private const val LOCATION_START_NUM = 0
    private const val BASIC_STRING = "C"

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
    }

    private fun getPrintString(cell: Cell): String {
        if(cell is Basic && cell.isOpen){
            return cell.count.toString()
        }
        return BASIC_STRING
    }
}
