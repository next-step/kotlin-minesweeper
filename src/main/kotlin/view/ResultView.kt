package view

import domain.MinesWeeper

object ResultView {

    private const val BOARD_PRINT_STRING = "\n지뢰찾기 게임 시작"

    private const val NEW_LINE_STRING = "\n"

    private const val BLANK_STRING = " "

    fun printGameBoard(minesWeeper: MinesWeeper) {
        println(BOARD_PRINT_STRING)
        val output = minesWeeper.boards.joinToString(NEW_LINE_STRING) { row ->
            row.joinToString(BLANK_STRING)
        }
        println(output)
    }
}
