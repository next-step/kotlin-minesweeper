package minesweeper.view.dto

import minesweeper.domain.Board
import minesweeper.domain.point.Mine

data class BoardDto(val board: List<List<PointDto>>) {
    companion object {
        fun from(board: Board): BoardDto {
            return BoardDto(
                board.points.map { rows -> rows.map { point -> PointDto(isMine = point is Mine) } },
            )
        }
    }
}
