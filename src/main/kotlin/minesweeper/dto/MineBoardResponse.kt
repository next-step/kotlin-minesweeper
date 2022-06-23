package minesweeper.dto

import minesweeper.domain.BoardFields
import minesweeper.domain.MineSweeper

class MineBoardResponse(
    val boardFields: BoardFields,
    val width: Int
) {
    companion object {
        fun of(mineSweeper: MineSweeper, width: Int): MineBoardResponse {
            return MineBoardResponse(mineSweeper.boardFields, width)
        }
    }
}
