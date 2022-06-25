package minesweeper.view

import minesweeper.domain.BoardField
import minesweeper.domain.BoardFields
import minesweeper.domain.MineField
import minesweeper.domain.NumberField
import minesweeper.dto.MineBoardResponse

object ResultView {
    private const val NOT_OPENED_FIELD_SYMBOL = "C"
    private const val MINE_FIELD_SYMBOL = "*"
    private const val FIELD_ROW_SEPARATOR = " "

    fun printGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(mineBoard: MineBoardResponse) {
        val boardFields = mineBoard.boardFields
        boardFields.boardFields.chunked(mineBoard.width)
            .forEach { println(convertBoardRow(it, boardFields)) }
    }

    fun printGameResult(win: Boolean) {
        if (win) {
            println("Win Game.")
        } else {
            println("Lose Game.")
        }
    }

    private fun convertBoardRow(boardFieldRow: List<BoardField>, boardFields: BoardFields): String {
        return boardFieldRow.joinToString(FIELD_ROW_SEPARATOR) { convertBoardField(it, boardFields) }
    }

    private fun convertBoardField(boardField: BoardField, boardFields: BoardFields): String {
        return if (!boardField.isOpen) {
            NOT_OPENED_FIELD_SYMBOL
        } else {
            convertOpenedField(boardField, boardFields)
        }
    }

    private fun convertOpenedField(boardField: BoardField, boardFields: BoardFields): String {
        return when (boardField) {
            is MineField -> MINE_FIELD_SYMBOL
            is NumberField -> boardField.number(boardFields).toString()
        }
    }
}
