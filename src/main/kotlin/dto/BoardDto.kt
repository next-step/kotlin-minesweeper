package dto

import domain.Blank
import domain.Board
import domain.Cell
import domain.Column
import domain.Mine
import domain.Status

data class BoardDto(
    val column: Column,
    val cells: List<String>
) {
    companion object {
        private const val CLOSED_CELL = "▣"
        private const val MINE_CELL = "*"

        fun from(board: Board, column: Column): BoardDto {
            return BoardDto(
                column,
                getSortedCells(board).map {
                    toString(it)
                }
            )
        }

        private fun toString(cell: Cell): String {
            if (cell.status == Status.CLOSE) return CLOSED_CELL

            return when (cell) {
                is Mine -> MINE_CELL
                is Blank -> cell.minesAroundCount.toString()
            }
        }

        private fun getSortedCells(board: Board): List<Cell> {
            return board.cells.sortedWith(compareBy({ it.coordinate.x.value }, { it.coordinate.y.value }))
        }
    }
}
