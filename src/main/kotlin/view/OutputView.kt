package view

import domain.Board
import domain.Land
import domain.Mine

object OutputView {
    private const val START_MINE_SWEEPER = "지뢰찾기 게임 시작"
    private const val LAND_STRING = "C"
    private const val WIN_GAME = "Win Game."
    private const val LOSE_GAME = "Lose Game."

    fun printStartGame() {
        println(START_MINE_SWEEPER)
    }

    fun printBoard(board: Board) {
        (0 until board.height.value).forEach { height ->
            (0 until board.width.value).forEach { width ->
                printField(board, height, width)
            }
            println()
        }
    }

    fun printWinGame() {
        println(WIN_GAME)
    }

    fun printLoseGame() {
        println(LOSE_GAME)
    }

    private fun printField(board: Board, height: Int, width: Int) {
        when (val field = board.getField(height, width)) {
            is Land -> printMineCntOrLandString(board, height, width, field.isOpened)
            is Mine -> print("$LAND_STRING ")
        }
    }

    private fun printMineCntOrLandString(board: Board, height: Int, width: Int, isOpened: Boolean) {
        when (isOpened) {
            true -> print("${board.getNearByMineCount(height, width)} ")
            false -> print("$LAND_STRING ")
        }
    }
}
