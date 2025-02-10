package mine.domain

import mine.dto.Coordinate
import mine.enums.MineCell

class BoardCalculator {
    fun calculateBoard(board: MineBoard): MineBoard {
        board.rows.forEachIndexed { x, row ->
            calculateRowValues(row, x, board)
        }
        return board
    }

    private fun calculateRowValues(
        row: MineRow,
        x: Int,
        board: MineBoard,
    ) {
        row.mineCells.forEachIndexed { y, cell ->
            if (cell is MineCell.Number) {
                cell.value = countAdjacentMines(x, y, board)
            }
        }
    }

    private fun countAdjacentMines(
        x: Int,
        y: Int,
        board: MineBoard,
    ): Int {
        return board.getNeighbors(Coordinate(x, y))
            .count { (nx, ny) -> board.getCell(Coordinate(nx, ny)) is MineCell.MINE }
    }

    companion object {
        const val CELL_NEGATIVE = -1
        const val CELL_ZERO = 0
        const val CELL_POSITIVE = 1
    }
}
