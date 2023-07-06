package view

import domain.Mine
import domain.MinesWeeper

object ResultView {

    private const val BOARD_PRINT_STRING = "\n지뢰찾기 게임 시작"

    private const val MINE_STRING = "*"

    private const val BASIC_STRING = "C"

    fun printGameBoard(width: Int, minesWeeper: MinesWeeper) {
        println(BOARD_PRINT_STRING)
        val chucks = minesWeeper.boards.sortedBy { it.location.x }.sortedBy { it.location.y }.chunked(width)
        for (chunk in chucks) {
            println(chunk.joinToString(" ") { getPrintString(it is Mine) })
        }
    }

    private fun getPrintString(isMine: Boolean): String {
        if (isMine) return MINE_STRING
        return BASIC_STRING
    }
}
