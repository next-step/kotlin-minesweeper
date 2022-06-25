package minesweeper.view

import minesweeper.dto.BoardFieldResponse
import minesweeper.dto.BoardRowResponse
import minesweeper.dto.MineFieldResponse
import minesweeper.dto.MineSweeperResponse
import minesweeper.dto.NotOpenedResponse
import minesweeper.dto.NumberFieldResponse

object ResultView {
    private const val NOT_OPENED_FIELD_SYMBOL = "C"
    private const val MINE_FIELD_SYMBOL = "*"
    private const val FIELD_SEPARATOR = " "

    fun printGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printMineSweeper(mineSweeperResponse: MineSweeperResponse) {
        val boardRowResponses = mineSweeperResponse.boardRowResponses
        boardRowResponses.forEach { printBoardRow(it) }
    }

    private fun printBoardRow(boardRowResponse: BoardRowResponse) {
        val boardRow = boardRowResponse.boardFieldResponses
            .joinToString(FIELD_SEPARATOR) { convertBoardField(it) }

        println(boardRow)
    }

    private fun convertBoardField(boardField: BoardFieldResponse): String {
        return when (boardField) {
            is MineFieldResponse -> MINE_FIELD_SYMBOL
            is NotOpenedResponse -> NOT_OPENED_FIELD_SYMBOL
            is NumberFieldResponse -> boardField.number.toString()
        }
    }

    fun printGameResult(win: Boolean) {
        if (win) {
            println("Win Game.")
        } else {
            println("Lose Game.")
        }
    }
}
