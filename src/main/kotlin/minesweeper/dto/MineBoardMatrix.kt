package minesweeper.dto

import minesweeper.domain.MineBoard
import minesweeper.domain.cell.Dot

data class MineBoardMatrix(
    val rows: List<Row>,
) {
    companion object {
        fun from(mineBoard: MineBoard, width: Int): MineBoardMatrix = MineBoardMatrix(
            mineBoard.cells
                .asSequence()
                .map { it.dot }
                .chunked(width)
                .map { Row(it) }
                .toList()
        )
    }
}

data class Row(
    val dots: List<Dot>,
)
