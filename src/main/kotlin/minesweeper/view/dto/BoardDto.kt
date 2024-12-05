package minesweeper.view.dto

import minesweeper.domain.Board
import minesweeper.domain.point.Mine

data class BoardDto(val board: List<List<Boolean>>) {
    companion object {
        fun from(board: Board): BoardDto {
            return BoardDto(
                board.points.map { points -> points.cols.map { point -> point is Mine } },
            )
        }
    }
}
