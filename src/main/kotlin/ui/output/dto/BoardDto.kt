package ui.output.dto

import domain.Board
import domain.Cell

data class BoardDto(
    val lines: List<Line>,
) {
    companion object {
        fun from(board: Board): BoardDto {
            val lines = board.cells.chunked(
                board.dimension.width
            ).map {
                Line(it)
            }
            return BoardDto(lines)
        }
    }
}

data class Line(
    val cells: List<Cell>
)
