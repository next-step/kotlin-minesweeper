package mine.domain

import mine.enums.MineCell

class BoardCalculator {
    fun calculateBoard(mineBoard: List<MineRow>): List<MineRow> {
        return mineBoard.mapIndexed { rowIndex, currentRow ->
            val beforeRow = mineBoard.getOrNull(rowIndex - 1)
            val afterRow = mineBoard.getOrNull(rowIndex + 1)
            calculateRow(currentRow, beforeRow, afterRow)
        }
    }

    private fun calculateRow(
        currentRow: MineRow,
        beforeRow: MineRow?,
        afterRow: MineRow?,
    ): MineRow {
        val updatedCells =
            currentRow.mineCells.mapIndexed { col, cell ->
                calculateCell(cell, beforeRow, currentRow, afterRow, col)
            }
        return MineRow(updatedCells)
    }

    private fun calculateCell(
        cell: MineCell,
        beforeRow: MineRow?,
        currentRow: MineRow,
        afterRow: MineRow?,
        col: Int,
    ): MineCell {
        return if (cell == MineCell.MINE) {
            MineCell.MINE
        } else {
            val calculatedMines = adjacentMineCalculate(beforeRow, currentRow, afterRow, col)
            MineCell.Number(calculatedMines)
        }
    }

    private fun adjacentMineCalculate(
        beforeRow: MineRow?,
        currentRow: MineRow,
        afterRow: MineRow?,
        col: Int,
    ): Int {
        return directions.sumOf { dx ->
            listOfNotNull(beforeRow, currentRow, afterRow).sumOf { row ->
                checkMine(row, col + dx)
            }
        }
    }

    private fun checkMine(
        row: MineRow,
        col: Int,
    ): Int {
        return if (row.isValidCell(col) && isMine(row.mineCells[col])) {
            MINE_ADD_VALUE
        } else MINE_NORMAL_VALUE
    }

    private fun isMine(mineCell: MineCell): Boolean {
        return mineCell == MineCell.MINE
    }

    companion object {
        private const val MINE_ADD_VALUE = 1
        private const val MINE_NORMAL_VALUE = 0
        const val CELL_NEGATIVE = -1
        const val CELL_ZERO = 0
        const val CELL_POSITIVE = 1
        private val directions =
            listOf(CELL_NEGATIVE, CELL_ZERO, CELL_POSITIVE)
    }
}
