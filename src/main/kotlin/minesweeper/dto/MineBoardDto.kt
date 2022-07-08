package minesweeper.dto

import minesweeper.model.MineBoard

class MineBoardDto private constructor(
    val boardRows: List<MineBoardRowDto>
) {

    companion object {
        fun from(board: MineBoard): MineBoardDto {
            val boardRows = board.board.map { MineBoardRowDto.of(it, board) }
            return MineBoardDto(boardRows)
        }
    }
}
