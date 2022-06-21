package minesweeper.view

import minesweeper.domain.BoardField
import minesweeper.domain.BoardFields
import minesweeper.domain.MineField
import minesweeper.domain.NumberField
import minesweeper.dto.MineBoardResponse

object ResultView {

    fun printBoard(mineBoard: MineBoardResponse) {
        println("지뢰찾기 게임 시작")
        val boardFields = mineBoard.boardFields
        boardFields.boardFields.chunked(mineBoard.width)
            .forEach { println(convertBoardRow(it, boardFields)) }
    }
}

private fun convertBoardRow(boardFieldRow: List<BoardField>, boardFields: BoardFields): String {
    return boardFieldRow.joinToString(" ") { convertBoardField(it, boardFields) }
}

private fun convertBoardField(boardField: BoardField, boardFields: BoardFields): String {
    return when (boardField) {
        is MineField -> "*"
        is NumberField -> boardField.number(boardFields).toString()
    }
}
