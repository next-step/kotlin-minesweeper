package minesweeper.converter

import minesweeper.entity.Cell
import minesweeper.entity.Coordinate
import minesweeper.entity.MineField

class MineFieldConverter {
    fun mapToViewData(mineField: MineField): List<List<Char>> {
        return List(mineField.height.value) { y ->
            List(mineField.width.value) { x ->
                val cell = mineField.findCell(Coordinate(x, y))
                convertCellToView(cell, mineField)
            }
        }
    }

    private fun convertCellToView(
        cell: Cell,
        mineField: MineField,
    ): Char {
        if (cell.isRevealed) {
            return revealedView(cell, mineField)
        }
        return HIDDEN_VIEW
    }

    private fun revealedView(
        cell: Cell,
        mineField: MineField,
    ) = when (cell) {
        is Cell.Mine -> MINE_VIEW
        is Cell.Empty -> emptyView(mineField, cell)
    }

    private fun emptyView(
        mineField: MineField,
        cell: Cell,
    ) = mineField.countAroundMines(cell.coordinate)
        .digitToChar()

    companion object {
        const val MINE_VIEW = '*'
        const val HIDDEN_VIEW = '?'
    }
}
