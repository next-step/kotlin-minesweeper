package minesweeper.view

import minesweeper.domain.BoardField
import minesweeper.dto.BoardRowResponse
import minesweeper.dto.MineBoardResponse

object ResultView {

    fun printBoard(mineBoard: MineBoardResponse) {
        println("지뢰찾기 게임 시작")
        mineBoard.boardRowResponses.forEach { println(convertBoardRow(it)) }
    }

    private fun convertBoardRow(boardRow: BoardRowResponse): String {
        return boardRow.boardFields.joinToString(" ") { convertBoardField(it) }
    }

    private fun convertBoardField(boardField: BoardField): String {
        return when (boardField.isMine) {
            true -> "*"
            false -> "C"
        }
    }
}
