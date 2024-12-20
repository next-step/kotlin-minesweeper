package mine.domain

import mine.enums.MineCell

class BoardCalculator {
    fun calculateBoard(
        board: List<MineRow>,
        height: Int,
        width: Int,
    ): List<MineRow> {
        val result: MutableList<MutableList<MineCell>> =
            MutableList(height) { MutableList(width) { MineCell.Number(MINE_CELL_DEFAULT_VALUE) } }
        board.forEachIndexed { row, mineRow ->
            mineRow.mineCells.forEachIndexed { col, cell ->
                isMine(cell, result, row, col, height, width)
            }
        }

        return result.map { MineRow(it) }
    }

    private fun isMine(
        cell: MineCell,
        result: MutableList<MutableList<MineCell>>,
        row: Int,
        col: Int,
        height: Int,
        width: Int,
    ) {
        if (cell == MineCell.MINE) {
            result[row][col] = MineCell.MINE
            updateNeighbors(result, row, col, height, width)
        }
    }

    private fun updateNeighbors(
        board: MutableList<MutableList<MineCell>>,
        row: Int,
        col: Int,
        height: Int,
        width: Int,
    ) {
        val directions =
            listOf(
                CELL_NEGATIVE to CELL_NEGATIVE,
                CELL_NEGATIVE to CELL_ZERO,
                CELL_NEGATIVE to CELL_POSITIVE,
                CELL_ZERO to CELL_NEGATIVE,
                CELL_ZERO to CELL_POSITIVE,
                CELL_POSITIVE to CELL_NEGATIVE,
                CELL_POSITIVE to CELL_ZERO,
                CELL_POSITIVE to CELL_POSITIVE,
            )
        directions.forEach { (dx, dy) ->
            val newRow = row + dx
            val newCol = col + dy
            isFindMineCell(newRow, height, newCol, width, board)
        }
    }

    private fun isFindMineCell(
        newRow: Int,
        height: Int,
        newCol: Int,
        width: Int,
        board: MutableList<MutableList<MineCell>>,
    ) {
        if (newRow in DEFAULT_START_VALUE until height &&
            newCol in DEFAULT_START_VALUE until width &&
            board[newRow][newCol] !is MineCell.MINE
        ) {
            val current = board[newRow][newCol] as MineCell.Number
            board[newRow][newCol] = MineCell.Number(current.value + 1)
        }
    }

    companion object {
        const val CELL_NEGATIVE = -1
        const val CELL_ZERO = 0
        const val CELL_POSITIVE = 1
        const val DEFAULT_START_VALUE = 0
        const val MINE_CELL_DEFAULT_VALUE = 0
    }
}
