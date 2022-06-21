package minesweeper.dto

import minesweeper.domain.BoardField
import minesweeper.domain.MineBoard

class MineBoardResponse(
    val boardRowResponses: List<BoardRowResponse>
) {
    companion object {
        fun of(mineBoard: MineBoard, width: Int): MineBoardResponse {
            val boardFields = mineBoard.boardFields.boardFields
            val boardRowResponses = boardFields.chunked(width)
                .map(::BoardRowResponse)
            return MineBoardResponse(boardRowResponses)
        }
    }
}

data class BoardRowResponse(
    val boardFields: List<BoardField>
)
