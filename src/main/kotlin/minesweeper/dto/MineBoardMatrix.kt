package minesweeper.dto

import minesweeper.domain.MineBoard
import minesweeper.domain.MineBoardLength
import minesweeper.domain.cell.Dot

data class MineBoardMatrix(
    val rows: List<Row>,
) {
    companion object {
        fun from(mineBoard: MineBoard, width: MineBoardLength): MineBoardMatrix = MineBoardMatrix(
            mineBoard.cells
                .asSequence()
                .map { it.dot }
                .chunked(width.value)
                .map { Row(it) }
                .toList()
        )
    }
}

data class Row(
    val dots: List<Dot>,
)
