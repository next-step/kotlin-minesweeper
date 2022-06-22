package minesweeper.dto

import minesweeper.domain.BoardFields
import minesweeper.domain.MineBoard

class MineBoardResponse(
    val boardFields: BoardFields,
    val width: Int
) {
    companion object {
        fun of(mineBoard: MineBoard, width: Int): MineBoardResponse {
            return MineBoardResponse(mineBoard.boardFields, width)
        }
    }
}
